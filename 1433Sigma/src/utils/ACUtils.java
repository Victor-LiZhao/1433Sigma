package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beans.Node;

/**
 * 使用auto covariance方法提取特征
 * 
 * @author LiZhao
 * 
 */
public class ACUtils {

	/**
	 * 给定一个sequence，按照ac方法生成特征
	 * @param node 需要生成特征的序列
	 * @return 包含特征的list
	 */
	public static List<Double> getACFeatures(Node node) {
		String seq = node.getSeq();
		List<Double> features = new ArrayList<Double>();
		char a1 = seq.charAt(0);
		char a2 = seq.charAt(1);
		char a3 = seq.charAt(2);
		Map<String, Double> a1Map = FeatureUtils.featureMap.get(a1);
		Map<String, Double> a2Map = FeatureUtils.featureMap.get(a2);
		Map<String, Double> a3Map = FeatureUtils.featureMap.get(a3);
		{// 计算第一个氨基酸和第二个氨基酸的interaction
			Set<String> keySet = a1Map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String propertyName = iterator.next();
				Double xij = a1Map.get(propertyName) + a2Map.get(propertyName)
						+ a3Map.get(propertyName);
				xij = xij / 3.0;
				Double x1j = a1Map.get(propertyName);
				Double x2j = a2Map.get(propertyName);
				Double feature = (x1j - xij) * (x2j - xij);
				features.add(feature);
			}
		}
		{// 计算第二个氨基酸和第三个氨基酸的interaction
			Set<String> keySet = a2Map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String propertyName = iterator.next();
				Double xij = a1Map.get(propertyName) + a2Map.get(propertyName)
						+ a3Map.get(propertyName);
				xij = xij / 3.0;
				Double x2j = a2Map.get(propertyName);
				Double x3j = a3Map.get(propertyName);
				Double feature = (x2j - xij) * (x3j - xij);
				features.add(feature);
			}
		}
		{// 计算第一个氨基酸和第三个氨基酸的interaction
			Set<String> keySet = a3Map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String propertyName = iterator.next();
				Double xij = a1Map.get(propertyName) + a2Map.get(propertyName)
						+ a3Map.get(propertyName);
				xij = xij / 3.0;
				Double x1j = a1Map.get(propertyName);
				Double x3j = a3Map.get(propertyName);
				Double feature = (x1j - xij) * (x3j - xij);
				features.add(feature);
			}
		}
		return features;
	}
	
	public static void  handleAndWrite(Node node2, List<Node> nodes,
			String outfilename){
		File file=new File(outfilename);
		PrintWriter pw=null;

		try {
			pw=new PrintWriter(new FileWriter(file));
			List<Double> listNode2=getACFeatures(node2);
			for(int i=0;i<listNode2.size();++i){
				pw.print(listNode2.get(i).floatValue()+"\t");
			}
			pw.print(Double.valueOf(node2.getRf()).floatValue()+"\n");
			
			for(int i=0;i<nodes.size();++i){
				Node node=nodes.get(i);
				List<Double> listNode=getACFeatures(node);
				for(int j=0;j<listNode.size();++j){
					pw.print(listNode.get(j).floatValue()+"\t");
				}
				pw.print(Double.valueOf(node.getRf()).floatValue()+"\n");
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			pw.close();
		}
	}
}
