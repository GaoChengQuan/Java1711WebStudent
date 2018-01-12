package com.situ.student.service.impl;

import java.util.List;

import javax.naming.InitialContext;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoImpl;
import com.situ.student.entity.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.util.Constant;
import com.situ.student.vo.PageBean;
import com.situ.student.vo.StudentSearchCondition;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao studentDao = new StudentDaoImpl();

	@Override
	public List<Student> findAll() {
		List<Student> list = studentDao.findAll();
		for (Student student : list) {
			
		}
		return studentDao.findAll();
	}

	@Override
	public int add(Student student) {
		//判断此用户名是否存在，如果存在就显示：此用户名已经存在
		if (studentDao.checkName(student.getName())) {
			return Constant.ADD_NAME_REPEAT;
		} else {//用户名不存在，可以直接添加到数据库
			// return studentDao.add(student) > 0 ? true : false;
			int count = studentDao.add(student);
			if (count > 0) {
				return Constant.ADD_SUCCESS;
			} 
			return Constant.ADD_FAIL;
		}
	}

	@Override
	public List<Student> showStudentAndBanjiInfo() {
		return studentDao.showStudentAndBanjiInfo();
	}

	@Override
	public List<Student> findByName(String name) {
		return studentDao.findByName(name);
	}

	@Override
	public boolean deleteById(int id) {
		if (studentDao.deleteById(id) > 0) {
			return true;
		} 
		
		return false;
		
		/*if (studentDao.deleteById(id) > 0) {
			return true;
		} else {
			return false;
		}
		*/
		
		/*int count = studentDao.deleteById(id);
		if (count > 0) {
			return true;
		} else {
			return false;
		}*/
	}

	@Override
	public Student findById(int id) {
		return studentDao.findById(id);
	}

	@Override
	public boolean update(Student student) {
		if (studentDao.update(student) > 0) {
			return true;
		} 
		
		return false;
	}

	@Override
public PageBean searchByCondition(StudentSearchCondition studentSearchCondition) {
	PageBean pageBean = new PageBean();
	int pageNo = studentSearchCondition.getPageNo();
	int pageSize = studentSearchCondition.getPageSize();
	// 当前是第几页 private Integer pageNo;
	pageBean.setPageNo(pageNo);
	// 一页有多少条数据 private Integer pageSize;
	pageBean.setPageSize(pageSize);
	// 总记录数 private Integer totalCount;
	// SELECT COUNT(*) FROM student;
	// SELECT COUNT(*) FROM student WHERE NAME LIKE '%张%' AND age=20;
	int totalCount = studentDao.getTotalCount(studentSearchCondition);
	// 一共有多少页 private Integer totalPage;
	int totalPage = (int) Math.ceil((double)totalCount / pageSize);
	pageBean.setTotalPage(totalPage);
	// 当前页的数据 private List<Student> list;
	//SELECT * FROM student LIMIT 3,3;
	//SELECT * FROM student WHERE NAME LIKE '%张%' AND age=20 LIMIT 3,3;
	List<Student> list = studentDao.findPageBeanList(studentSearchCondition);
	pageBean.setList(list);
	return pageBean;
}

	@Override
	public PageBean getPageBean(int pageNo, int pageSize) {
		PageBean pageBean = new PageBean();
		// 当前是第几页 private Integer pageNo;
		pageBean.setPageNo(pageNo);
		// 一页有多少条数据 private Integer pageSize;
		pageBean.setPageSize(pageSize);
		// 总记录数 private Integer totalCount;
		int totalCount = studentDao.getTotalCount();
		// 一共有多少页 private Integer totalPage;
		/**
		 * 总条数	每页的条数  	 总页数
		 * 10			3		 4
		 * 11			3		 4
		 * 12			3		 4
		 * 13			3		 5
		 */
		int totalPage = (int) Math.ceil((double)totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		// 当前页的数据 private List<Student> list;
		int offset = (pageNo - 1) * pageSize;
		List<Student> list = studentDao.findPageBeanList(offset, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public boolean checkName(String name) {
		return studentDao.checkName(name);
	}
	
	
	
	
	
	
	
	
	
	

}
