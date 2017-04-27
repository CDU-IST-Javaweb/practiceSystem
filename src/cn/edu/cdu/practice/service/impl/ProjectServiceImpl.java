package cn.edu.cdu.practice.service.impl;

import java.util.Calendar;


import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
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
		int m = projectDaoImpl.countProject();
		if (m >= 0) {
			int no = m + 1;
			int year = Calendar.getInstance().get(Calendar.YEAR) * 1000000;
			return year + no + "";
		}
		return null;
	}

	@Override
	public int getStuGrade(int n) {
		Calendar date = Calendar.getInstance();
		if (date.get(Calendar.MONTH) > 8) {
			return date.get(Calendar.YEAR) - n + 1;
		} else
			return date.get(Calendar.YEAR) - n;
	}

}
