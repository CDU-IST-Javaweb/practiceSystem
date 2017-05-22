package cn.edu.cdu.practice.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.dao.impl.SystemParameterDaoImpl;
import cn.edu.cdu.practice.model.Project;
import cn.edu.cdu.practice.model.SystemParameter;
import cn.edu.cdu.practice.service.ProjectService;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @date 创建时间：2017年4月26日 下午6:38:41
 * @FileName:ProjectServiceImpl.java
 * @version 1.0
 * @Description:
 * @Author:杨永浩
 * @Modification User:
 * @Modification Date:
 */
public class ProjectServiceImpl implements ProjectService {

	@Override
	public String getProjectNo() {
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		int m = projectDaoImpl.findMaxProjectNo(Calendar.getInstance().get(Calendar.YEAR));
		if (m > 0) {
			return m + 1 + "";
		} else if (m == 0) {
			return Calendar.getInstance().get(Calendar.YEAR) + "000001";
		} else {
			return null;
		}
	}

	@Override
	public int getStuGrade(int n) {
		Calendar date = Calendar.getInstance();
		if (date.get(Calendar.MONTH) > 8) {
			return date.get(Calendar.YEAR) - n + 1;
		} else
			return date.get(Calendar.YEAR) - n;
	}

	@Override
	public boolean findProjectBelongToUserByPNo(String username, String p_no) {
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		Project project = projectDaoImpl.findProjectByNo(p_no);
		if (project.getCompanyUsername().equals(username))
			return true;
		return false;
	}

	@Override
	public boolean findPracticeIsUnderWay() {
		SystemParameterDaoImpl systemParameterDaoImpl = new SystemParameterDaoImpl();
		SystemParameter systemParameter = systemParameterDaoImpl.queryByAccount("admin");
		Date data = Calendar.getInstance().getTime();
		if (systemParameter == null) {
			return false;
		} else if (systemParameter.getStudentSelStartDate().before(data)
				&& systemParameter.getStudentSelEndDate().after(data)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean findAddPracticeIsUnderWay() {
		SystemParameterDaoImpl systemParameterDaoImpl = new SystemParameterDaoImpl();
		SystemParameter systemParameter = systemParameterDaoImpl.queryByAccount("admin");
		Date data = Calendar.getInstance().getTime();
		if (systemParameter == null) {
			return false;
		} else if (systemParameter.getReleaseProjectStartDate().before(data)
				&& systemParameter.getReleaseProjectEndDate().after(data)) {
			return true;
		}
		return false;
	}

	@Override
	public int[] findAllAddProjectYear() {
		int PRACTICE_SYSTEM_START_YEAR = 2017;
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);
		int len = nowYear - PRACTICE_SYSTEM_START_YEAR + 1;
		int years[] = new int[len];
		for (int i = 0; i < len; i++) {
			years[i] = PRACTICE_SYSTEM_START_YEAR + i;
		}
		return years;
	}

	@Override
	public ArrayList<String> findAllProfessional() {
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		ArrayList<String> professionals = projectDaoImpl.findAllProfessional();
		return professionals;
	}

}
