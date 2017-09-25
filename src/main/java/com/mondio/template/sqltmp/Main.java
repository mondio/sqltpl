package com.mondio.template.sqltmp;

import com.mondio.template.sqltmp.table.*;

/**
 * 程序入口
 */
public class Main {
	public static String basePath = System.getProperty("user.dir");
	public static String separator = System.getProperty("line.separator");

	public static void main(String[] args) {
		kj_master_salary.satrt(50, "161", "1-6");
		kj_master_area.satrt(50, "161", "1-6");
		kj_master_industry.satrt(50, "161", "1-6");
		kj_master_occupation.satrt(50, "161", "1-6");

		kj_master_section.satrt(50, "161", "1-6");
		kj_master_keisai_han.satrt(100, "161", "1-6"); // 注意文件中存在回车符问题 需要手动修改
		kj_kengen_info.satrt(100, "161", "1-6");
		kj_code_master.satrt("161", "1-6");
		kj_master_houjinn_kaku.satrt("161", "1-6");
		kj_master_employment_type.satrt("161", "1-6");
		kj_master_ataku.satrt("161", "1-6");
		kj_master_ataku_result.satrt("161", "1-6");
		
		
//		kj_code_master.satrt("161", "1-6");
	}
}
