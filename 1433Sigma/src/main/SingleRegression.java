package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beans.Node;
import beans.PCValue;
import beans.RSFValue;
import utils.ACUtils;
import utils.FeatureUtils;
import utils.FileUtils;
import utils.PCVUtils;
import utils.SMOTERUtils;

/**
 * 单条元组做回归
 * 
 * @author 朝
 * 
 */
public class SingleRegression {

	public static void main(String[] args) {
		// List<PCValue> listPcv=FileUtils.getPcvMatrix();
		// Map<Character,PCValue> map=PCVUtils.makePCVMap(listPcv);
		// List<Node> nodes=FileUtils.readData();
		// handleAndWrite(nodes,map);
		test();
	}

	private static void test() {
		List list = readTestData();
		List resultList = getTestResult(list);
		writeResult(resultList);
	}

	private static void writeResult(List result) {
		String outfilename = "d:testResult.txt";
		File file = new File(outfilename);
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new FileWriter(file));
			for (int i = 0; i < result.size(); ++i) {
				String s[] = (String[]) result.get(i);
				pw.println(s[0] + "\t" + s[1] + "\t" + s[2]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}

	private static List getTestResult(List list) {
		List resultList = new ArrayList();
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			String s[] = (String[]) iterator.next();
			double result = 0.0;
			result = 6387.060 + Double.valueOf(s[2]) * (-194.410)
					+ Double.valueOf(s[3]) * 18.406 + Double.valueOf(s[5])
					* (-3856.637) + Double.valueOf(s[7]) * 2875.612
					+ Double.valueOf(s[9]) * (-377.327) + Double.valueOf(s[10])
					* (-8.724) + Double.valueOf(s[11]) * (-160.296)
					+ Double.valueOf(s[12]) * (7893.010)
					+ Double.valueOf(s[13]) * (-1489.864)
					+ Double.valueOf(s[14]) * (-2437.517)
					+ Double.valueOf(s[16]) * (-529.241)
					+ Double.valueOf(s[17]) * (21.108) + Double.valueOf(s[18])
					* (-102.108) + Double.valueOf(s[19]) * (2071.888)
					+ Double.valueOf(s[20]) * (-2396.745)
					+ Double.valueOf(s[21]) * (-2154.543);
			String writes[] = new String[3];
			writes[0] = s[0];
			writes[1] = s[22];
			writes[2] = String.valueOf(result);
			resultList.add(writes);
		}
		return resultList;
	}

	private static List readTestData() {
		List list = new ArrayList();
		String filename = "d:test.txt";
		BufferedReader br = null;
		File file = new File(filename);
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				String sTemp[] = line.split("\t");
				list.add(sTemp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("test data size number=" + list.size());
		return list;
	}

	public static void handleAndWrite(Node node2, List<Node> nodes,
			Map<Character, PCValue> map, String outfilename, String outfilename2) {
		File file = new File(outfilename);
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new FileWriter(file));
			{
				PCValue pcv0 = map.get(node2.getSeq().charAt(0));
				PCValue pcv1 = map.get(node2.getSeq().charAt(1));
				PCValue pcv2 = map.get(node2.getSeq().charAt(2));
				// pw.println("test\t"+node2.getSeq()+"\t"+pcv0.getAa()+"\t"+pcv0.getH().floatValue()+"\t"+pcv0.getVsc().floatValue()+"\t"+pcv0.getP1().floatValue()+"\t"+pcv0.getP2().floatValue()+"\t"+pcv0.getSasa().floatValue()+"\t"+pcv0.getNcisc().floatValue()
				// +"\t"+pcv1.getAa()+"\t"+pcv1.getH().floatValue()+"\t"+pcv1.getVsc().floatValue()+"\t"+pcv1.getP1().floatValue()+"\t"+pcv1.getP2().floatValue()+"\t"+pcv1.getSasa().floatValue()+"\t"+pcv1.getNcisc().floatValue()
				// +"\t"+pcv2.getAa()+"\t"+pcv2.getH().floatValue()+"\t"+pcv2.getVsc().floatValue()+"\t"+pcv2.getP1().floatValue()+"\t"+pcv2.getP2().floatValue()+"\t"+pcv2.getSasa().floatValue()+"\t"+pcv2.getNcisc().floatValue()
				// +"\t"+node2.getRf());
			}
			for (Node node : nodes) {
				PCValue pcv0 = map.get(node.getSeq().charAt(0));
				PCValue pcv1 = map.get(node.getSeq().charAt(1));
				PCValue pcv2 = map.get(node.getSeq().charAt(2));
				pw.println(pcv0.getH().floatValue() + "\t"
						+ pcv0.getVsc().floatValue() + "\t"
						+ pcv0.getP1().floatValue() + "\t"
						+ pcv0.getP2().floatValue() + "\t"
						+ pcv0.getSasa().floatValue() + "\t"
						+ pcv0.getNcisc().floatValue() + "\t"
						+ pcv1.getH().floatValue() + "\t"
						+ pcv1.getVsc().floatValue() + "\t"
						+ pcv1.getP1().floatValue() + "\t"
						+ pcv1.getP2().floatValue() + "\t"
						+ pcv1.getSasa().floatValue() + "\t"
						+ pcv1.getNcisc().floatValue() + "\t"
						+ pcv2.getH().floatValue() + "\t"
						+ pcv2.getVsc().floatValue() + "\t"
						+ pcv2.getP1().floatValue() + "\t"
						+ pcv2.getP2().floatValue() + "\t"
						+ pcv2.getSasa().floatValue() + "\t"
						+ pcv2.getNcisc().floatValue() + "\t" + node.getRf());
				// pw.println(node.getRf()+" 1:"+pcv0.getH()+" 2:"+pcv0.getVsc()+" 3:"+pcv0.getP1()+" 4:"+pcv0.getP2()+" 5:"+pcv0.getSasa()+" 6:"+pcv0.getNcisc()
				// +" 7:"+pcv1.getH()+" 8:"+pcv1.getVsc()+" 9:"+pcv1.getP1()+" 10:"+pcv1.getP2()+" 11:"+pcv1.getSasa()+" 12:"+pcv1.getNcisc()
				// +" 13:"+pcv2.getH()+" 14:"+pcv2.getVsc()+" 15:"+pcv2.getP1()+" 16:"+pcv2.getP2()+" 17:"+pcv2.getSasa()+" 18:"+pcv2.getNcisc());
				//
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
		}

		// File file2=new File(outfilename2);
		// PrintWriter pw2=null;
		//
		// try {
		// pw2=new PrintWriter(new FileWriter(file2));
		// {
		// PCValue pcv0=map.get(node2.getSeq().charAt(0));
		// PCValue pcv1=map.get(node2.getSeq().charAt(1));
		// PCValue pcv2=map.get(node2.getSeq().charAt(2));
		// //
		// pw.println("test\t"+node2.getSeq()+"\t"+pcv0.getAa()+"\t"+pcv0.getH().floatValue()+"\t"+pcv0.getVsc().floatValue()+"\t"+pcv0.getP1().floatValue()+"\t"+pcv0.getP2().floatValue()+"\t"+pcv0.getSasa().floatValue()+"\t"+pcv0.getNcisc().floatValue()
		// //
		// +"\t"+pcv1.getAa()+"\t"+pcv1.getH().floatValue()+"\t"+pcv1.getVsc().floatValue()+"\t"+pcv1.getP1().floatValue()+"\t"+pcv1.getP2().floatValue()+"\t"+pcv1.getSasa().floatValue()+"\t"+pcv1.getNcisc().floatValue()
		// //
		// +"\t"+pcv2.getAa()+"\t"+pcv2.getH().floatValue()+"\t"+pcv2.getVsc().floatValue()+"\t"+pcv2.getP1().floatValue()+"\t"+pcv2.getP2().floatValue()+"\t"+pcv2.getSasa().floatValue()+"\t"+pcv2.getNcisc().floatValue()
		// // +"\t"+node2.getRf());
		// pw2.println(node2.getRf()+" 1:"+pcv0.getH()+" 2:"+pcv0.getVsc()+" 3:"+pcv0.getP1()+" 4:"+pcv0.getP2()+" 5:"+pcv0.getSasa()+" 6:"+pcv0.getNcisc()
		// +" 7:"+pcv1.getH()+" 8:"+pcv1.getVsc()+" 9:"+pcv1.getP1()+" 10:"+pcv1.getP2()+" 11:"+pcv1.getSasa()+" 12:"+pcv1.getNcisc()
		// +" 13:"+pcv2.getH()+" 14:"+pcv2.getVsc()+" 15:"+pcv2.getP1()+" 16:"+pcv2.getP2()+" 17:"+pcv2.getSasa()+" 18:"+pcv2.getNcisc());
		// }
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }finally
		// {
		// pw2.close();
		// }

	}

	/**
	 * 用rsf 3个属性值做回归
	 * 
	 * @param node2
	 * @param nodes
	 * @param map
	 * @param outfilename
	 * @param outfilename2
	 */
	public static void handleAndWrite_RSF(Node node2, List<Node> nodes,
			Map<Character, RSFValue> map, String outfilename) {
		File file = new File(outfilename);
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new FileWriter(file));
			{
				RSFValue rsf0 = map.get(node2.getSeq().charAt(0));
				RSFValue rsf1 = map.get(node2.getSeq().charAt(1));
				RSFValue rsf2 = map.get(node2.getSeq().charAt(2));
				pw.println("test\t" + node2.getSeq() + "\t" + rsf0.getAa()
						+ "\t" + rsf0.getComposition().floatValue() + "\t"
						+ rsf0.getPolarity().floatValue() + "\t"
						+ rsf0.getVolume().floatValue() + "\t" + rsf1.getAa()
						+ "\t" + rsf1.getComposition().floatValue() + "\t"
						+ rsf1.getPolarity().floatValue() + "\t"
						+ rsf1.getVolume().floatValue() + "\t" + rsf2.getAa()
						+ "\t" + rsf2.getComposition().floatValue() + "\t"
						+ rsf2.getPolarity().floatValue() + "\t"
						+ rsf2.getVolume().floatValue() + "\t" + node2.getRf());
			}
			for (Node node : nodes) {
				RSFValue rsf0 = map.get(node.getSeq().charAt(0));
				RSFValue rsf1 = map.get(node.getSeq().charAt(1));
				RSFValue rsf2 = map.get(node.getSeq().charAt(2));
				pw.println(node.getSeq() + "\t" + rsf0.getAa() + "\t"
						+ rsf0.getComposition().floatValue() + "\t"
						+ rsf0.getPolarity().floatValue() + "\t"
						+ rsf0.getVolume().floatValue() + "\t" + rsf1.getAa()
						+ "\t" + rsf1.getComposition().floatValue() + "\t"
						+ rsf1.getPolarity().floatValue() + "\t"
						+ rsf1.getVolume().floatValue() + "\t" + rsf2.getAa()
						+ "\t" + rsf2.getComposition().floatValue() + "\t"
						+ rsf2.getPolarity().floatValue() + "\t"
						+ rsf2.getVolume().floatValue() + "\t" + node.getRf());

				// pw.println(node.getRf()+" 1:"+pcv0.getH()+" 2:"+pcv0.getVsc()+" 3:"+pcv0.getP1()+" 4:"+pcv0.getP2()+" 5:"+pcv0.getSasa()+" 6:"+pcv0.getNcisc()
				// +" 7:"+pcv1.getH()+" 8:"+pcv1.getVsc()+" 9:"+pcv1.getP1()+" 10:"+pcv1.getP2()+" 11:"+pcv1.getSasa()+" 12:"+pcv1.getNcisc()
				// +" 13:"+pcv2.getH()+" 14:"+pcv2.getVsc()+" 15:"+pcv2.getP1()+" 16:"+pcv2.getP2()+" 17:"+pcv2.getSasa()+" 18:"+pcv2.getNcisc());
				//
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

	public static void handleAndWrite_new(Node node2, List<Node> nodes,
			String outfilename) {
		File file = new File(outfilename);
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new FileWriter(file));

			for (int i = 0; i < 3; i++) {
				Map<String, Double> map = FeatureUtils.featureMap.get(node2
						.getSeq().charAt(i));
				Set<String> keySet = map.keySet();
				Iterator<String> iterator = keySet.iterator();
				while (iterator.hasNext()) {
					String property_name = iterator.next();
					pw.print(map.get(property_name).floatValue() + "\t");
				}

			}
			pw.print(Double.valueOf(node2.getRf()).floatValue() + "\n");
			for (Node node : nodes) {
				for (int i = 0; i < 3; i++) {
					Map<String, Double> map = FeatureUtils.featureMap.get(node
							.getSeq().charAt(i));
					Set<String> keySet = map.keySet();
					Iterator<String> iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String property_name = iterator.next();
						pw.print(map.get(property_name).floatValue() + "\t");
					}
				}
				pw.print(Double.valueOf(node.getRf()).floatValue() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}

	/**
	 * 将所有特征都写入文件中
	 * 
	 * @param node2
	 * @param nodes
	 * @param outfilename
	 */
	public static void handleAndWrite_All(Node node2, List<Node> nodes,
			String outfilename) {
		File file = new File(outfilename);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(file));

			for (int i = 0; i < 3; i++) {
				Map<String, Double> map = FeatureUtils.featureMap.get(node2
						.getSeq().charAt(i));
				Set<String> keySet = map.keySet();
				Iterator<String> iterator = keySet.iterator();
				while (iterator.hasNext()) {
					String property_name = iterator.next();
					pw.print(map.get(property_name).floatValue() + "\t");
				}

			}
			List<Double> listNode2 = ACUtils.getACFeatures(node2);
			for (int i = 0; i < listNode2.size(); ++i) {
				pw.print(listNode2.get(i).floatValue() + "\t");
			}
			pw.print(Double.valueOf(node2.getRf()).floatValue() + "\n");
			for (Node node : nodes) {
				for (int i = 0; i < 3; i++) {
					Map<String, Double> map = FeatureUtils.featureMap.get(node
							.getSeq().charAt(i));
					Set<String> keySet = map.keySet();
					Iterator<String> iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String property_name = iterator.next();
						pw.print(map.get(property_name).floatValue() + "\t");
					}
				}
				List<Double> listNode = ACUtils.getACFeatures(node);
				for (int j = 0; j < listNode.size(); ++j) {
					pw.print(listNode.get(j).floatValue() + "\t");
				}
				pw.print(Double.valueOf(node.getRf()).floatValue() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}

	public static List<Double[]> handle(Node node2, List<Node> nodes) {
		List<Double[]> list = new ArrayList<Double[]>();
		List<Double[]> newList = new ArrayList<Double[]>();

		// 将待测试节点放入list中
		{
			List<Double> tempList = new ArrayList<Double>();
			for (int i = 0; i < 3; i++) {
				Map<String, Double> map = FeatureUtils.featureMap.get(node2
						.getSeq().charAt(i));
				Set<String> keySet = map.keySet();
				Iterator<String> iterator = keySet.iterator();
				while (iterator.hasNext()) {
					String property_name = iterator.next();
					tempList.add(map.get(property_name));
				}

			}

			List<Double> listNode2 = ACUtils.getACFeatures(node2);
			for (int i = 0; i < listNode2.size(); ++i) {
				//tempList.add(listNode2.get(i));
			}
			tempList.add(Double.valueOf(node2.getRf()));
			Double[] tempArray = new Double[tempList.size()];
			tempList.toArray(tempArray);
			newList.add(tempArray);
		}

		// 将其他训练节点放入list中
		for (Node node : nodes) {
			List<Double> tempList = new ArrayList<Double>();
			for (int i = 0; i < 3; i++) {
				Map<String, Double> map = FeatureUtils.featureMap.get(node
						.getSeq().charAt(i));
				Set<String> keySet = map.keySet();
				Iterator<String> iterator = keySet.iterator();
				while (iterator.hasNext()) {
					String property_name = iterator.next();
					tempList.add(map.get(property_name));
				}
			}
			List<Double> listNode = ACUtils.getACFeatures(node);
			for (int j = 0; j < listNode.size(); ++j) {
				//tempList.add(listNode.get(j));
			}
			tempList.add(Double.valueOf(node.getRf()));
			Double[] tempArray = new Double[tempList.size()];
			tempList.toArray(tempArray);
			list.add(tempArray);
		}
		Double[] tempArray=newList.get(0);
		newList.clear();
		newList.addAll(SMOTERUtils.getSmoteR(list,tempArray, list.get(0).length - 1,
				1000.0, 300, 50, 5));
		return newList;
	}

	public static void writeNewData(List<Double[]> newlist, String outfilename) {
		File file = new File(outfilename);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(file));
			for(int i=0;i<newlist.size();++i){
				Double[] temp=newlist.get(i);
				for(int j=0;j<temp.length;++j)
					pw.print(temp[j]+"\t");
				pw.print("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

}
