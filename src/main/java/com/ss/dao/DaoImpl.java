package com.ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.common.Constant;
import com.ss.common.Response;
import com.ss.common.ReturnCodes;
import com.ss.dto.StudentDetails;
import com.ss.entity.dao.StudentDao;

public class DaoImpl implements DaoInterface {

	@Autowired
	DataSource dataSource;
	
	//INSERT a Student
	public Response<StudentDetails> saveStudent(ArrayList<StudentDetails> regInfo) {
		Response<StudentDetails> response = new Response<StudentDetails>();
		Map<String, String> errorMap = new HashMap<String, String>();
		int action=0;
		
		try{
			Connection conn = this.dataSource.getConnection();
			
			for(StudentDetails res:regInfo){
				
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(StudentDao.saveStudentData);
				
				pst.setString(1, res.getsNo());	
				pst.setString(2, res.getsName());
				pst.setString(3, res.getfName());
				pst.setString(4, res.getGrade());
				action = pst.executeUpdate();
			}
			}catch(Exception e){
				e.printStackTrace();
				errorMap.put("Message", "Error occurred while saving data."+e);
			}
			
			if(action == 1){
				response.setErrorsMap(errorMap);
				response.setResponseCode(ReturnCodes.OPERATION_SUCCESSFUL);
				response.setResponseDesc(Constant.SUCCESSFUL);
			}else{
				response.setErrorsMap(errorMap);
				response.setResponseCode(ReturnCodes.GENERIC_BUSINESS_ERROR);
				response.setResponseDesc(Constant.FAILURE);
			}
			return response;
		}
	
     //search All Students List
	public Response<ArrayList<StudentDetails>> selectStudent() {
		Response<ArrayList<StudentDetails>> response = new Response<ArrayList<StudentDetails>>();
		List<ArrayList<StudentDetails>> wraplist = new ArrayList<ArrayList<StudentDetails>>();
		Map<String, String> errorMap = new HashMap<String, String>();
		boolean returnString = false;
		
		StudentDetails sd=null;
		ArrayList<StudentDetails> student= null;
		
		try{
			Connection conn = this.dataSource.getConnection();
			PreparedStatement pst1 = (PreparedStatement) conn.prepareStatement(StudentDao.selectStudentData);
			ResultSet rs=pst1.executeQuery();
			
			student=new ArrayList<StudentDetails>();
		
			if(!rs.next()){
				errorMap.put("Message", "No Any Student Found");
			}else{
				
				do{
					sd=new StudentDetails();
				 sd.setsNo(rs.getString("student_number"));
				 sd.setsName(rs.getString("student_name"));
				 sd.setfName(rs.getString("father_name"));
				 sd.setGrade(rs.getString("grade"));
				
				 student.add(sd);
				 returnString=true;
				
	            }while(rs.next());
				 wraplist.add(student);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			errorMap.put("Message", "Error occurred while saving data."+e);
		}
		
		if(returnString){
			response.setWrappedList(wraplist);
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.OPERATION_SUCCESSFUL);
			response.setResponseDesc(Constant.SUCCESSFUL);
		}else{
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.GENERIC_BUSINESS_ERROR);
			response.setResponseDesc(Constant.FAILURE);
		}
		return response;
	}
	
	//Search With Student number
	public Response<StudentDetails> selectStudentByNo(StudentDetails noInfo) {
		
		Response<StudentDetails> response = new Response<StudentDetails>();
		List<StudentDetails> wraplist = new ArrayList<StudentDetails>();
		Map<String, String> errorMap = new HashMap<String, String>();
		boolean returnString = false;
		
		StudentDetails sd=null;
		
		try{
			Connection conn = this.dataSource.getConnection();
			PreparedStatement pst1 = (PreparedStatement) conn.prepareStatement(StudentDao.selectStudentByNo);
			pst1.setString(1, noInfo.getsNo());
			
			ResultSet rs=pst1.executeQuery();
			
			if(!rs.next()){
				errorMap.put("Message", "No any Student EXIST as "+noInfo.getsNo()+" please register a new Student");
			}else{
				
				 sd=new StudentDetails();
				 
				 sd.setsNo(rs.getString("student_number"));
				 sd.setsName(rs.getString("student_name"));
				 sd.setfName(rs.getString("father_name"));
				 sd.setGrade(rs.getString("grade"));
				
				 wraplist.add(sd);
				 returnString=true;				
	          }
			
		}catch(Exception e){
			e.printStackTrace();
			errorMap.put("Message", "Error occurred while saving data."+e);
		}
		
		if(returnString){
			response.setWrappedList(wraplist);
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.OPERATION_SUCCESSFUL);
			response.setResponseDesc(Constant.SUCCESSFUL);
		}else{
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.GENERIC_BUSINESS_ERROR);
			response.setResponseDesc(Constant.FAILURE);
		}
		return response;
	}
	
	//UPDATE by Student number
	public Response<StudentDetails> updateStudentByNo(StudentDetails noInfo) {
		Response<StudentDetails> response = new Response<StudentDetails>();
		List<StudentDetails> wraplist = new ArrayList<StudentDetails>();
		Map<String, String> errorMap = new HashMap<String, String>();
		boolean returnString = false;
		
		boolean b =false;
		StudentDetails sd=null;
		//String name = noInfo.getsName();
		try{
			Connection conn = this.dataSource.getConnection();
			PreparedStatement pst1 = (PreparedStatement) conn.prepareStatement(StudentDao.updateStudentByNo);
			pst1.setString(1, noInfo.getsName());
			pst1.setString(2, noInfo.getsNo());
			
			pst1.executeUpdate();
			b=true;
			
			if(!b){
				errorMap.put("Message", "No any Student EXIST as "+noInfo.getsNo()+" please register a new Student");
			}else{
				try{
					
					PreparedStatement pst2 = (PreparedStatement) conn.prepareStatement(StudentDao.selectStudentByNo);
					pst2.setString(1, noInfo.getsNo());
					
					ResultSet rs1=pst2.executeQuery();
					
				if(rs1.next()) {
				
				 sd=new StudentDetails();
				 sd.setsNo(rs1.getString("student_number"));
				 sd.setsName(rs1.getString("student_name"));
				 sd.setfName(rs1.getString("father_name"));
				 sd.setGrade(rs1.getString("grade"));
				}
				 wraplist.add(sd);
				 returnString=true;	
				//conn.close(); 
	          }catch(SQLException e){
	  			e.printStackTrace();
				errorMap.put("Message", "Error occurred while Internal saving data."+e);
			}
		}
			
		}catch(Exception e){
			e.printStackTrace();
			errorMap.put("Message", "Error occurred while saving data."+e);
		}
		
		if(returnString){
			response.setWrappedList(wraplist);
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.OPERATION_SUCCESSFUL);
			response.setResponseDesc(Constant.SUCCESSFUL);
		}else{
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.GENERIC_BUSINESS_ERROR);
			response.setResponseDesc(Constant.FAILURE);
		}
		return response;
	}
	
	//Delete Student
	public Response<StudentDetails> deleteStudentByNo(StudentDetails noInfo) {
		Response<StudentDetails> response = new Response<StudentDetails>();
		List<StudentDetails> wraplist = new ArrayList<StudentDetails>();
		Map<String, String> errorMap = new HashMap<String, String>();
		boolean returnString = false;
		
		boolean b=false;
		try{
			Connection conn = this.dataSource.getConnection();
			PreparedStatement pst1 = (PreparedStatement) conn.prepareStatement(StudentDao.deleteStudentByNo);
			pst1.setString(1, noInfo.getsNo());
	
			pst1.executeUpdate();
			b=true;
			if(!b){
				errorMap.put("Message", "No any Student EXIST as "+noInfo.getsNo()+" OR may be Allready Deleted.");
			}else{
				errorMap.put("Message", "Student "+noInfo.getsNo()+" Deleted Deleted.");
				 returnString=true;				
	          }
			
		}catch(Exception e){
			e.printStackTrace();
			errorMap.put("Message", "Error occurred while saving data."+e);
		}
		
		if(returnString){
			response.setWrappedList(wraplist);
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.OPERATION_SUCCESSFUL);
			response.setResponseDesc(Constant.SUCCESSFUL);
		}else{
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.GENERIC_BUSINESS_ERROR);
			response.setResponseDesc(Constant.FAILURE);
		}
		return response;
	}
	
	///Turncate Table
	public Response<StudentDetails> turncateStudenttable() {
		Response<StudentDetails> response = new Response<StudentDetails>();
		List<StudentDetails> wraplist = new ArrayList<StudentDetails>();
		Map<String, String> errorMap = new HashMap<String, String>();
		boolean returnString = false;
		boolean b=false;
		try{
			Connection conn = this.dataSource.getConnection();
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(StudentDao.turncateStudentData);
			
			pst.executeUpdate();
			b=true;
			if(!b){
				errorMap.put("Message", "No any Student Table EXIST  OR may be Allready Deleted.");
			}else{
				errorMap.put("Message", "Student Table Delete Now.");
				 returnString=true;				
	          }
			
		}catch(Exception e){
			e.printStackTrace();
			errorMap.put("Message", "Error occurred while saving data."+e);
		}
		
		if(returnString){
			response.setWrappedList(wraplist);
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.OPERATION_SUCCESSFUL);
			response.setResponseDesc(Constant.SUCCESSFUL);
		}else{
			response.setErrorsMap(errorMap);
			response.setResponseCode(ReturnCodes.GENERIC_BUSINESS_ERROR);
			response.setResponseDesc(Constant.FAILURE);
		}
		return response;
	}

	

}