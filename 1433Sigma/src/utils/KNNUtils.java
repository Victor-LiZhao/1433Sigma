package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KNNUtils {
	/**
	 * ���ݸ��������ݼ��Լ�����������λ�ã����������k��case
	 * @param dataSet   �������ݼ�List��������Ҫ�ҵ��Ǹ�case
	 * @param dem  ÿһ��������ά��
	 * @param k  ��Ҫָ����k
	 * @param index  ��Ҫ�ҵ�case������λ��
	 * @return ����k��nn��list
	 */
public static List<Double[]> getKnn(List<Double[]> dataSet,int dem, int k,int index){
	if(dataSet.size()<=0) {
		System.out.println("�������ݼ�Ϊ�գ�");
		return null;
	}
	if(dataSet.get(0).length-1!=dem){
		System.out.println("ERROR!��������ά���봫�����ݲ�����");
		return null;
	}
	Map<Double,Integer> distMap=new HashMap<Double,Integer>();
	for(int i=0;i<dataSet.size();++i){
		if(i!=index){	
			Double[] caseData=dataSet.get(index);    //��Ҫ��nn���Ǹ�case
			Double[] tempData=dataSet.get(i);
			Double dist=0.0;
			for(int j=0;j<(caseData.length-1);++j){
				dist+=Math.pow((caseData[j]-tempData[j]),2);
			}
			dist=Math.sqrt(dist);
			distMap.put(dist, i);
		}
	}
	Object[] keySet=distMap.keySet().toArray();
	
	Arrays.sort(keySet);
	List<Double[]> list=new ArrayList<Double[]>();
	for(int i=0;i<keySet.length&&i<k;++i){
		int indexTemp=distMap.get(keySet[i]);
		list.add(dataSet.get(indexTemp));
	}
	return list;
	
}
public static void main(String[] args) {
	Double[] d1={1.0,1.0,3.0};
	Double[] d2={5.0,1.0,4.0};
	Double[] d3={4.0,4.0,4.0};
	List<Double[]> list=new ArrayList<Double[]>();
	list.add(d3);
	list.add(d2);
	list.add(d1);
	List<Double[]> lists=getKnn(list, 2, 1, 0);
}
}
