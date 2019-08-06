package com.ss.controller;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ss.common.Response;
import com.ss.dto.StudentDetails;
import com.ss.service.ServiceInterface;

@Controller
@Path("/Sushil")
public class MasterController {

	@Autowired
	ServiceInterface userServices;
	
	
	@POST
	@Path("/saveStudent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Response<StudentDetails> saveStudent(ArrayList<StudentDetails> regInfo){
		Response<StudentDetails> response = new Response<StudentDetails>();
		try{
			response = userServices.saveStudent(regInfo);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	return response;
	}
	
	////
	@POST
	@Path("/selectStudent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Response<ArrayList<StudentDetails>> selectStudent(){
		Response<ArrayList<StudentDetails>> response = new Response<ArrayList<StudentDetails>>();
		try{
			response = userServices.selectStudent();
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	return response;
	}
	
	    ////
		@POST
		@Path("/serchByNo")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		
		public Response<StudentDetails> selectStudentByNo(StudentDetails noInfo){
			Response<StudentDetails> response = new Response<StudentDetails>();
			try{
				response = userServices.selectStudentByNo(noInfo);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}	
		return response;
		}
		////////
		@POST
		@Path("/updateByNo")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		
		public Response<StudentDetails> updateStudentByNo(StudentDetails noInfo){
			Response<StudentDetails> response = new Response<StudentDetails>();
			try{
				response = userServices.updateStudentByNo(noInfo);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}	
		return response;
		}
			////////
			@POST
			@Path("/deleteByNo")
			@Produces(MediaType.APPLICATION_JSON)
			@Consumes(MediaType.APPLICATION_JSON)
			
			public Response<StudentDetails> deleteStudentByNo(StudentDetails noInfo){
				Response<StudentDetails> response = new Response<StudentDetails>();
				try{
					response = userServices.deleteStudentByNo(noInfo);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}	
			return response;
			}
			
			////////
			@POST
			@Path("/turncateTable")
			@Produces(MediaType.APPLICATION_JSON)
			@Consumes(MediaType.APPLICATION_JSON)				
			public Response<StudentDetails> turncateStudenttable(){
						Response<StudentDetails> response = new Response<StudentDetails>();
						try{
							response = userServices.turncateStudenttable();
							
						}
						catch(Exception e){
							e.printStackTrace();
						}	
					return response;
					}
					
}