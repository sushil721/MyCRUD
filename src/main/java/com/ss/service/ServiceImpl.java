package com.ss.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.common.Response;
import com.ss.dao.DaoInterface;
import com.ss.dto.StudentDetails;

public class ServiceImpl implements ServiceInterface {
	
	@Autowired
	DaoInterface daoInterface;


	public Response<StudentDetails> saveStudent(ArrayList<StudentDetails> regInfo) {
		// TODO Auto-generated method stub
		return daoInterface.saveStudent(regInfo);
	}


	public Response<ArrayList<StudentDetails>> selectStudent() {
		// TODO Auto-generated method stub
		return daoInterface.selectStudent();
	}


	public Response<StudentDetails> selectStudentByNo(StudentDetails noInfo) {
		// TODO Auto-generated method stub
		return daoInterface.selectStudentByNo(noInfo);
	}


	public Response<StudentDetails> updateStudentByNo(StudentDetails noInfo) {
		
		return daoInterface.updateStudentByNo(noInfo);
	}


	public Response<StudentDetails> deleteStudentByNo(StudentDetails noInfo) {
		// TODO Auto-generated method stub
		return  daoInterface.deleteStudentByNo(noInfo);
	}


	public Response<StudentDetails> turncateStudenttable() {
		// TODO Auto-generated method stub
		return  daoInterface.turncateStudenttable();
	}


	


}
