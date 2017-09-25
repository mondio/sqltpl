package com.mondio.template.sqltmp.table;

import java.util.List;

import com.mondio.template.sqltmp.Main;
import com.mondio.template.sqltmp.util.Util;
import com.xiaoleilu.hutool.io.file.FileReader;
import com.xiaoleilu.hutool.io.file.FileWriter;
import com.xiaoleilu.hutool.util.StrUtil;
/**
 * kj_code_master
 * @author hezhe
 */
public class kj_code_master {
	public static void satrt(String update_per_code,String contract_company_id){
		String childName="kj_code_master";
		String fileTplPath=Main.basePath+"/template/"+childName+"/";
		String fileResultPath=Main.basePath+"/result/"+childName+"/";
		FileReader fileChildReader = new FileReader(fileTplPath+childName+".sql");
		StringBuffer sb = new StringBuffer();
		List<String> resultChlidList = fileChildReader.readLines();//读取主表
		int cNum=0;
		for (String line : resultChlidList) {
			if(StrUtil.isNotBlank(line) && line.toUpperCase().contains("VALUES (")){
				//截取vaules括号内部的值
				String[] values =line.substring(line.indexOf("VALUES (")+8,line.lastIndexOf(")")).split("', '");
				values[4]="CURRENT_TIMESTAMP";
				values[5]="'"+update_per_code+"'";
				values[7]="'"+contract_company_id+"'";
				sb.append(Util.creatSql(childName,values)+Main.separator);
				cNum++;
			}
		}
		FileWriter writer = new FileWriter(fileResultPath+childName+".sql");
		writer.write(sb.toString());
		System.out.println(childName+" 生成成功"+(cNum)+"条。");
	}
}
