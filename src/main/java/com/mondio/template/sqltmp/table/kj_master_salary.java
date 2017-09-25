package com.mondio.template.sqltmp.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mondio.template.sqltmp.Main;
import com.mondio.template.sqltmp.util.Util;
import com.xiaoleilu.hutool.io.file.FileReader;
import com.xiaoleilu.hutool.io.file.FileWriter;
import com.xiaoleilu.hutool.util.StrUtil;
/**
 * kj_master_salary
 * @author hezhe
 */
public class kj_master_salary {
	public static void satrt(Integer seqId,String update_per_code,String contract_company_id){
		String parentName="kj_master_salary_type";
		String childName="kj_master_salary";
		String parentSeq="nextval('kj_master_salary_type_seq')";
		String childSeq="nextval('kj_master_salary_seq')";
		
		String fileTplPath=Main.basePath+"/template/"+childName+"/";
		String fileResultPath=Main.basePath+"/result/"+childName+"/";
		FileReader fileParentReader = new FileReader(fileTplPath+parentName+".sql");
		List<String> resultParentList = fileParentReader.readLines();//读取主表
		Map<String, String> seqMap = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		int pNum=0;
		for (String line : resultParentList) {
			if(StrUtil.isNotBlank(line) && line.toUpperCase().contains("VALUES (")){
				//截取vaules括号内部的值
				String[] values =line.substring(line.indexOf("VALUES (")+8,line.lastIndexOf(")")).split("', '");
				String bId=Util.replaceFEchar(values[0]);
				String nId=(seqId++)+"";
				seqMap.put(bId, nId);
				values[0]=parentSeq;
				values[3]="CURRENT_TIMESTAMP";
				values[4]="'"+update_per_code+"'";
				values[6]="'"+contract_company_id+"'";
				sb.append(Util.creatSql(parentName,values)+Main.separator);
				pNum++;
			}
		}
		
		FileReader fileChildReader = new FileReader(fileTplPath+childName+".sql");
		List<String> resultChlidList = fileChildReader.readLines();//读取主表
		int cNum=0;
		int errorNum=0;
		for (String line : resultChlidList) {
			if(StrUtil.isNotBlank(line) && line.toUpperCase().contains("VALUES (")){
				//截取vaules括号内部的值
				String[] values =line.substring(line.indexOf("VALUES (")+8,line.lastIndexOf(")")).split("', '");
				values[0]=childSeq;
				String newId=seqMap.get(Util.replaceFEchar(values[6]));
				if(newId==null){errorNum++;
					continue;
				}
				values[6]="'"+newId+"'";
				values[7]="CURRENT_TIMESTAMP";
				values[8]="'"+update_per_code+"'";
				values[10]="'"+contract_company_id+"'";
				sb.append(Util.creatSql(childName,values)+Main.separator);
				cNum++;
			}
		}
		FileWriter writer = new FileWriter(fileResultPath+childName+".sql");
		writer.write(sb.toString());
		System.out.println(childName+" 生成成功"+(pNum+cNum)+"条。主表："+pNum+" 子表："+cNum+" 异常："+errorNum);
	}
}
