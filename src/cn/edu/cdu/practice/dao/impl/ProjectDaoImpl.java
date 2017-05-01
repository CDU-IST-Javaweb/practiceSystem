package cn.edu.cdu.practice.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

import cn.edu.cdu.practice.dao.ProjectDao;
import cn.edu.cdu.practice.model.Project;
import cn.edu.cdu.practice.model.ProjectSelect;
import cn.edu.cdu.practice.model.ProjectSelectId;
import cn.edu.cdu.practice.model.Student;
import cn.edu.cdu.practice.service.impl.ProjectServiceImpl;
import cn.edu.cdu.practice.utils.DbUtils;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.PageUtils;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @date 创建时间：2017年4月18日 下午4:19:39
 * @FileName:ProjectDaoImpl.java
 * @version 1.0
 * @Description:
 * @Author:杨永浩
 * @Modification User:
 * @Modification Date:
 */
public class ProjectDaoImpl implements ProjectDao {

	@Override
	public boolean addProject(Project p) {
		String sql = "INSERT INTO project(no,name,introduction,students_num,company_username,"
				+ "release_date,grade,category,major,company_teacher,company_teacher_title) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		// 通过projectServiceImpl得到no
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		String no = projectServiceImpl.getProjectNo();
		try {
			if (no == null)
				return false;
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(1, no);
			ps.setString(2, p.getName());
			ps.setString(3, p.getIntroduction());
			ps.setInt(4, p.getStudentsNum());
			ps.setString(5, p.getCompanyUsername());
			ps.setDate(6, new Date(Calendar.getInstance().getTime().getTime()));
			ps.setInt(7, p.getGrade());
			ps.setString(8, p.getCategory());
			ps.setString(9, p.getMajor());

			// 前面为必需属性，后面为可选
			ps.setString(10, p.getCompanyTeacher());
			ps.setString(11, p.getCompanyTeacherTitle());
			ps.execute();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}

		return false;
	}

	@Override
	public boolean updateProject(Project p) {
		// 该sql语句用于查询传入方案是否已审核，审核后不能修改
		String sql1 = "SELECT audit_date FROM project WHERE No=?";
		// 修改方案属性sql语句
		String sql2 = "UPDATE project SET name=?,introduction=?,students_num=?,company_username=?,"
				+ "grade=?,category=?,major=?,company_teacher=?,company_teacher_title=? " + "WHERE no=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql1);
			ps.setString(1, p.getNo());
			rs = ps.executeQuery();
			Date date = null;
			if (rs.next())
				date = rs.getDate("audit_date");
			// 审核时间不为空表示已审核，不能修改
			if (date != null)
				return false;
			else {
				ps.close();
				connection.setAutoCommit(false);
				ps = connection.prepareStatement(sql2);
				ps.setString(1, p.getName());
				ps.setString(2, p.getIntroduction());
				ps.setInt(3, p.getStudentsNum());
				ps.setString(4, p.getCompanyUsername());
				ps.setInt(5, p.getGrade());
				ps.setString(6, p.getCategory());
				ps.setString(7, p.getMajor());
				ps.setString(8, p.getCompanyTeacher());
				ps.setString(9, p.getCompanyTeacherTitle());
				ps.setString(10, p.getNo());
				ps.executeUpdate();
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return false;
	}

	@Override
	public ArrayList<Project> findAllProject(int role, String company_username, PageUtils pageUtils) {
		String sql = null;
		int totalSize = 0;
		if (role == 9) {
			sql = "SELECT * FROM project LIMIT ?,?";
			totalSize = countProject();
			pageUtils.setTotalSize(totalSize);
		} else if (role == 1 && company_username != null) {
			sql = "SELECT * FROM project WHERE company_username=? LIMIT ?,?";
			totalSize = countCompanyProject(company_username);
			pageUtils.setTotalSize(totalSize);
		} else
			return null;
		if (totalSize <= 0)
			return null;
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			if (role == 9) {
				ps.setInt(1, start);
				ps.setInt(2, size);
			} else {
				ps.setString(1, company_username);
				ps.setInt(2, start);
				ps.setInt(3, size);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return projects;
	}

	@Override
	public boolean deleteProject(String p_no) {
		// 该sql语句用于查询传入方案是否已审核，审核后不能修改
		String sql1 = "SELECT audit_date FROM project WHERE No=?";
		String sql2 = "DELETE FROM project WHERE No=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql1);
			ps.setString(1, p_no);
			rs = ps.executeQuery();
			Date date = null;
			if (rs.next())
				date = rs.getDate("audit_date");
			// 审核时间不为空表示已审核，不能修改
			if (date != null)
				return false;
			else {
				ps.close();
				connection.setAutoCommit(false);
				Log4jUtils.info("测试删除");
				ps = connection.prepareStatement(sql2);
				ps.setString(1, p_no);
				ps.execute();
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return false;
	}

	@Override
	public boolean checkProject(String p_no, boolean check) {
		String sql = "UPDATE project SET audit_date=? WHERE no=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(2, p_no);
			if (!check) {
				ps.setString(1, null);
			} else {
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				ps.setDate(1, date);
			}
			ps.executeUpdate();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	@Override
	public boolean summaryProject(String p_no, String content) {
		String sql = "UPDATE project SET summary=? WHERE no=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(1, content);
			ps.setString(2, p_no);
			ps.executeUpdate();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	@Override
	public boolean endProjects(String[] p_nos) {
		String sql = "UPDATE project SET end_date=? WHERE no=?";
		Connection connection = DbUtils.getConnection();
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			for (int i = 0; i < p_nos.length; i++) {
				ps = connection.prepareStatement(sql);
				ps.setDate(1, date);
				ps.setString(2, p_nos[i]);
				ps.executeUpdate();
				connection.commit();
				ps.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}

		return false;
	}

	@Override
	public ArrayList<Project> findAllProject(int grade) {
		String sql = "SELECT * FROM project WHERE grade>=? and end_date is NULL and audit_date is not NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, grade);
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
			return projects;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return null;
	}

	@Override
	public boolean chooseProject(String company_name, String p_no, int stu_no, String reason) {
		// 查询单个学生已选方案数的sql语句
		String sql1 = "SELECT COUNT(*) m FROM project_select WHERE studentNo=?";
		// 查询系统预设学生可选方案数上限的sql语句
		String sql2 = "SELECT student_sel_maxnum m FROM system_parameter";
		// 增加学生选择方案的sql语句
		String sql3 = "UPDATE INTO project_select(studentNo,projectNo,sel_reason,company_name) VALUES(?,?,?,?)";

		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询学生已选方案数
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, stu_no);
			rs = ps.executeQuery();
			int stu_sel_max = 0;
			if (rs.next())
				stu_sel_max = rs.getInt("m");
			ps.close();
			rs.close();
			// 查询系统预设单个学生可选方案数上限
			ps = connection.prepareStatement(sql2);
			rs = ps.executeQuery();
			int sys_sel_max = 0;
			if (rs.next())
				sys_sel_max = rs.getInt("m");
			ps.close();
			rs.close();
			// 如果学生已选方案总数小于系统设置的上限，进行添加操作
			if (stu_sel_max < sys_sel_max) {
				ps = connection.prepareStatement(sql3);
				ps.setInt(1, stu_no);
				ps.setString(2, p_no);
				ps.setString(3, reason);
				ps.setString(4, company_name);
				ps.executeUpdate();
				connection.commit();
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	@Override
	public boolean unChooseProject(String p_no, int stu_no) {
		String sql = "DELETE FROM project_select WHERE studentNo=? and projectNo=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, stu_no);
			ps.setString(2, p_no);
			ps.execute();
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	// 这里仅仅只是返回了一个方案下的学生学号,未完善 查视图返回详细信息
	@Override
	public ArrayList<Integer> findAllStudentChoice(String p_no) {
		String sql = "SELECT studentNo FROM project_select WHERE projectNo=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				int stu_no = rs.getInt("studentNo");
				list.add(stu_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return list;
	}

	@Override
	public boolean chooseStudent(int stu_no, String p_no) {
		// 查看当前学生是否已有确定方案的sql语句
		String sql1 = "SELECT company_sel_date FROM project_select WHERE studentNo=? AND company_sel_date IS NOT NULL";
		// 选择学生的sql语句
		String sql2 = "UPDATE project_select SET company_sel_date=?  WHERE studentNo=? AND projectNo=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询学生已确定方案
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, stu_no);
			rs = ps.executeQuery();
			if (!rs.next()) {
				// 没有已确定方案
				ps.close();
				connection.setAutoCommit(false);
				ps = connection.prepareStatement(sql2);
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				ps.setDate(1, date);
				ps.setInt(2, stu_no);
				ps.setString(3, p_no);
				ps.executeUpdate();
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return false;
	}

	@Override
	public boolean unChooseStudent(int[] stu_nos, String p_no) {
		String sql = "UPDATE project_select SET company_sel_date=NULL  WHERE studentNo=? AND projectNo=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(2, p_no);
			for (int i = 0; i < stu_nos.length; i++) {
				ps.setInt(1, stu_nos[i]);
				ps.executeUpdate();
			}
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}
		return false;
	}

	@Override
	public ArrayList<ProjectSelect> findReason(int type, String p_no) {
		String sql = null;
		if (type == 1) {
			sql = "SELECT * FROM project_select WHERE company_sel_date IS NOT NULL";
		} else if (type == 2) {
			sql = "SELECT * FROM project_select WHERE company_sel_date IS NULL";
		} else if (type == 3) {
			sql = "SELECT * FROM project_select WHERE projectNo=?";
		} else
			return null; // 参数传入错误
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProjectSelect> selects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			if (type == 3)
				ps.setString(1, p_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProjectSelect projectSelect = new ProjectSelect();
				projectSelect.setCompanyName(rs.getString("company_name"));
				projectSelect.setCompanySelDate(rs.getDate(""));
				projectSelect.setId(new ProjectSelectId(rs.getString("studentNo"), rs.getInt("projectNo")));
				projectSelect.setScore(rs.getString("score"));
				projectSelect.setSelReason(rs.getString("sel_reason"));
				selects.add(projectSelect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return selects;
	}

	@Override
	public boolean inputScore(int[] stu_nos, int[] scores, String p_no) {
		String sql = "UPDATE project_select SET score=? WHERE studentNo=? AND projectNo=? AND company_sel_date IS NOT NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setString(3, p_no);
			for (int i = 0; i < stu_nos.length; i++) {
				ps.setInt(1, scores[i]);
				ps.setInt(2, stu_nos[i]);
				ps.executeUpdate();
			}
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps, null);
		}

		return false;
	}

	@Override
	public ArrayList<ProjectSelect> findScore(String p_no) {
		// 根据方案号查询已被选择的学生的成绩的sql语句
		String sql = "SELECT * FROM project_select WHERE projectNo=? AND company_sel_date IS NOT NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProjectSelect> selects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProjectSelect projectSelect = new ProjectSelect();
				projectSelect.setCompanyName(rs.getString("company_name"));
				projectSelect.setCompanySelDate(rs.getDate(""));
				projectSelect.setId(new ProjectSelectId(rs.getString("studentNo"), rs.getInt("projectNo")));
				projectSelect.setScore(rs.getString("score"));
				projectSelect.setSelReason(rs.getString("sel_reason"));
				selects.add(projectSelect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return selects;
	}

	@Override
	public int countProject() {
		// 查询方案总数的sql语句
		String sql = "SELECT COUNT(*) m FROM project";

		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			int m = 0;
			if (rs.next()) {
				m = rs.getInt("m");
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public int countCompanyProject(String company_username) {
		// 查询方案总数的sql语句
		String sql = "SELECT COUNT(*) m FROM project WHERE company_username=?";

		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			ps.setString(1, company_username);
			rs = ps.executeQuery();
			int m = 0;
			if (rs.next())
				m = rs.getInt("m");
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public Project findProjectByNo(String no) {
		String sql = "SELECT * FROM project WHERE No=?";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Project project = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return project;
	}

	@Override
	public int findMaxProjectNo(int year) {
		String sql = "SELECT MAX(`No`) m FROM project WHERE `No` LIKE '"+year+"%'";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 查询方案总数
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			String m = "";
			if (rs.next()) {
				m = rs.getString("m");
				return Integer.parseInt(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return -1;
	}

	@Override
	public ArrayList<Project> findAllStartedProject() {
		String sql = "SELECT * FROM project WHERE end_date is NULL and audit_date is not NULL";
		Connection connection = DbUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Project> projects = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setAuditDate(rs.getDate("audit_date"));
				project.setCategory(rs.getString("category"));
				project.setCompanyTeacher(rs.getString("company_teacher"));
				project.setCompanyTeacherTitle(rs.getString("company_teacher_title"));
				project.setCompanyUsername(rs.getString("company_username"));
				project.setEndDate(rs.getDate("end_date"));
				project.setGrade(rs.getInt("grade"));
				project.setIntroduction(rs.getString("introduction"));
				project.setMajor(rs.getString("major"));
				project.setName(rs.getString("name"));
				project.setNo(rs.getString("no"));
				project.setReleaseDate(rs.getDate("release_date"));
				project.setStudentsNum(rs.getInt("students_num"));
				project.setSummary(rs.getString("summary"));
				projects.add(project);
			}
			return projects;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(connection, ps, rs);
		}
		return null;
	}
}
