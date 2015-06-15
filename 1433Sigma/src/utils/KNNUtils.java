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
	 * 根据给定的数据集以及给定的索引位置，返回最近的k个case
	 * @param dataSet   所有数据集List，包含需要找的那个case
	 * @param dem  每一个样本的维度
	 * @param k  需要指定的k
	 * @param index  需要找的case的索引位置
	 * @return 包含k个nn的list
	 */
public static List<Double[]> getKnn(List<Double[]> dataSet,int dem, int k,int index){
	if(dataSet.size()<=0) {
		System.out.println("输入数据集为空！");
		return null;
	}
	if(dataSet.get(0).length-1!=dem){
		System.out.println("ERROR!输入样本维度与传入数据不符！");
		return null;
	}
	Map<Double,Integer> distMap=new HashMap<Double,Integer>();
	for(int i=0;i<dataSet.size();++i){
		if(i!=index){	
			Double[] caseData=dataSet.get(index);    //需要找nn的那个case
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
