package model.dao;

//import fr.lndr.jdbc.ArticleDao;
//import fr.lndr.jdbc.ArticleDaoJDBC;
import db.DB;

import model.dao.impl.CourseDaoJDBC;

public class DaoFactory {
	
	public static CourseDao createCourseDao()	{
		return new CourseDaoJDBC(DB.getConnection());
	}
	
	

}