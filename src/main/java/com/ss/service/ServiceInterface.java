package com.ss.service;

import java.util.ArrayList;

import com.ss.common.Response;
import com.ss.dto.StudentDetails;

public interface ServiceInterface {
	public Response<StudentDetails> saveStudent(ArrayList<StudentDetails> regInfo);
	public Response<ArrayList<StudentDetails>> selectStudent() ;
	public Response<StudentDetails> selectStudentByNo(StudentDetails noInfo);
	public Response<StudentDetails> updateStudentByNo(StudentDetails noInfo);
	public Response<StudentDetails> deleteStudentByNo(StudentDetails noInfo);
	public Response<StudentDetails> turncateStudenttable();
}
