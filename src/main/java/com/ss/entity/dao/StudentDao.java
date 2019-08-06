package com.ss.entity.dao;

import com.ss.common.TableConstants;

public class StudentDao {
	public static String saveStudentData = "INSERT INTO "+TableConstants.INSERT_STUDENT+
			"(`student_number`, `student_name`, `father_name`, `grade`"
			+ ") VALUES (?,?,?,?)";
	
	public static String selectStudentData = "SELECT * FROM "+TableConstants.SELECT_STUDENT;
	
	public static String selectStudentByNo =
		"SELECT student_number,student_name,father_name,grade FROM "+TableConstants.SELECT_STUDENT_BY_NO+" WHERE student_number = ?"; 
	
	public static String updateStudentByNo =
			"UPDATE "+TableConstants.UPDATE_STUDENT_BY_NO+" SET student_name = ? WHERE student_number = ?"; 
		
	public static String deleteStudentByNo =
			"DELETE FROM "+TableConstants.DELETE_STUDENT_BY_NO+" WHERE student_number = ?"; 
		
	//public static String turncateStudentData = "DELETE FROM "+TableConstants.TURNCATE_STUDENT;       
	//  OR
	public static String turncateStudentData = "TRUNCATE "+TableConstants.TURNCATE_STUDENT;
	
	
	
}
