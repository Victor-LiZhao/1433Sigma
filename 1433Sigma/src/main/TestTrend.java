package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ACUtils;
import utils.Blosum62Utils;
import utils.FileUtils;
import utils.PCVUtils;
import utils.RSFUtils;
import beans.CoupleUnit;
import beans.Node;
import beans.PCValue;
import beans.RSFValue;
import beans.UnitSigmas;

/**
 * 做分类  测定其趋势
 * @author 朝
 *
 */
public class TestTrend {

	
	
	public static UnitSigmas getUnit(Node node1,Node node2){
		
		double xs[]=new double[3]; 
		UnitSigmas unit=new UnitSigmas();
		for(int i=0;i<3;++i)
		{
			char A=node1.getSeq().charAt(i);
			char B=node2.getSeq().charAt(i);
			xs[i]=getdist(A,B);
		}
		unit.setSeqA(node1.getSeq());
		unit.setSeqB(node2.getSeq());
		unit.setRfA(node1.getRf());
		unit.setRfB(node2.getRf());
		unit.setX1(xs[0]);
		unit.setX2(xs[1]);
		unit.setX3(xs[2]);
		unit.setY(Math.abs(unit.getRfA()-unit.getRfB()));
		return unit;
	}
	
	private static double getdist(char a, char b) {
		Map<Character,Integer> map=new HashMap<Character,Integer>();
////七类
//		map.put('A',1 );
//		map.put('G',1);
//		map.put('V',1 );
//		map.put('I', 2);
//		map.put('L', 2);
//		map.put('F', 2);
//		map.put('P', 2);
//		map.put('Y', 3);
//		map.put('M', 3);
//		map.put('T', 3);
//		map.put('S', 3);
//		map.put('H', 4);
//		map.put('N', 4);
//		map.put('Q', 4);
//		map.put('W', 4);
//		map.put('R', 5);
//		map.put('K', 5);
//		map.put('D', 6);
//		map.put('E', 6);
//		map.put('C', 7);

		//五类
		map.put('R',1 );
		map.put('H', 1);
		map.put('K', 1);
		map.put('D', 2);
		map.put('E', 2);
		map.put('S', 3);
		map.put('T', 3);
		map.put('N', 3);
		map.put('Q', 3);
		map.put('C', 4);
		map.put('G', 4);
		map.put('P', 4);
		map.put('A', 5);
		map.put('V', 5);
		map.put('I', 5);
		map.put('L',5 );
		map.put('M', 5);
		map.put('F', 5);
		map.put('Y',5 );
		map.put('W',5 );
		int v1,v2;
		if(map.containsKey(a)&&map.containsKey(b))
		{
			v1=map.get(a);
			v2=map.get(b);
			if(v1==v2)
				return 1;
			else
				return 0;
		}
		System.out.println("ERROR! Error Residue!");
		return 0;
	}

	public static List<UnitSigmas> handle(List<Node> nodes){
		List<UnitSigmas> list=new ArrayList<UnitSigmas>();
		
		for(int i=0;i<nodes.size();i++)
		{
			Node node1=nodes.get(i);
			for(int j=i+1;j<nodes.size();j++)
			{
				Node node2=nodes.get(j);
				list.add(getUnit(node1,node2));
			}
		}
//		for(Node node1:nodes){
//			for(Node node2:nodes){
//				if(node1.getSeq().equals(node2.getSeq())){}
//				else{
//					list.add(getUnit(node1,node2));
//				}
//			}
//		}
		
		Collections.sort(list, new Comparator<UnitSigmas>() {
            public int compare(UnitSigmas arg0, UnitSigmas arg1) {
                return arg0.getY().compareTo(arg1.getY());
            }
        });
		
		return list;
	}
	
	public static void writeData(List<UnitSigmas> list,String outfilename)
	{
		File file=new File(outfilename);
		PrintWriter pw=null;
		

		try {
			pw=new PrintWriter(new FileWriter(file));
			
//			double sum=0;
//			for(int i=0;i<list.size();++i)
//			{
//				sum=sum+list.get(i).getX1()+list.get(i).getX2()+list.get(i).getX3();
//				if(i%1000==0)
//				{
//					System.out.println("i="+i+",sum="+sum);
//					pw.println(i+"\t"+sum);
//					sum=0;
//				}
//			}
			
			for(UnitSigmas unit:list)
			{
				String line=unit.parse2String();
				pw.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			pw.close();
		}
	}
	public static void main(String[] args) {
		
		List<Node> list=FileUtils.readData();
//		List<PCValue> listPcv=FileUtils.getPcvMatrix();
//		Map<Character,PCValue> map=PCVUtils.makePCVMap(listPcv);
		//List<UnitSigmas> list2=handle(list);
		//writeData(list2);
		for(int i=0;i<500;++i)
		{
			Node node=list.get(i);
			List<Node> dataSet=getDataSet(i,list);
//			List<CoupleUnit> coupleUnitList=getCoupleUnitList(dataSet,map);
//			writeCoupleData(i,list,coupleUnitList);
			//String outfilename1="d:pcvOriginal/single-"+i+".txt";
			//String outfilename1="d:pcvAC/single-"+i+".txt";
			String outfilename="d:data-norm/zeta-right-9pro-ac/single-"+i+".txt";
			//SingleRegression.handleAndWrite(node,dataSet, map,outfilename1,outfilename1);
			//SingleRegression.handleAndWrite_new(node,dataSet,outfilename1);
			//ACUtils.handleAndWrite(node, dataSet, outfilename1);
			//List<Double[]> newlist=SingleRegression.handle(node, dataSet);
			//SingleRegression.writeNewData(newlist,outfilename);
			SingleRegression.handleAndWrite_All( node,dataSet, outfilename);
		}
		
//  预测rsf代码
//		List<Node> list=FileUtils.readData();
//		testDataset_All(list);
//		List<RSFValue> listRsf=FileUtils.getRsfMatrix();
//		Map<Character,RSFValue> map=RSFUtils.makeRSFMap(listRsf);
//		for(int i=0;i<500;++i)
//		{
//			Node node=list.get(i);
//			List<Node> dataSet=getDataSet(i,list);
//			String outfilename1="d:rsfOriginal/single-"+i+".txt";
//			SingleRegression.handleAndWrite_RSF(node,dataSet, map,outfilename1);
//			
//			
//		}
		
		
//     预测所有8000种可能
//		List<Node> list=FileUtils.readData();
//		List<PCValue> listPcv=FileUtils.getPcvMatrix();
//		Map<Character,PCValue> map=PCVUtils.makePCVMap(listPcv);
//		String aa="RHKDESTNQCGPAILMFWYV";
//		int sum=0;
//		int i=0,j=0,k=0;
//		for(i=0;i<20;i++)
//			for(j=0;j<20;j++)
//				for(k=0;k<20;k++)
//				{
//					List <Node> dataSet=null;
//					Node node=null;
//					if(sum<500){
//						node=list.get(sum);
//						dataSet=getDataSet_All(node.getSeq(),list);
//					}
//					else
//					{
//					String seq=String.valueOf(aa.charAt(i))+String.valueOf(aa.charAt(j))+String.valueOf(aa.charAt(k));
//					node=new Node();
//					node.setSeq(seq);
//					node.setRf(-9999.999);
//					node.setRank(-10000);
//					
//					dataSet=getDataSet_All(seq, list);
//					}
//					String outfilename1="d:pcvOriginal/single-"+sum+".txt";
//					sum++;
//					SingleRegression.handleAndWrite(node,dataSet, map,outfilename1,outfilename1);
//				}
	}

	/**
	 * 将计算得到的数据写入文件留作回归模型用
	 * @param i 需预测的第i个点
	 * @param list 所有的数据表
	 * @param coupleUnitList 得到的双元组数据集
	 */
	private static void writeCoupleData(int i, List<Node> list,
			List<CoupleUnit> coupleUnitList) {
		String outfilename="d:data-"+i+".txt";
		File file=new File(outfilename);
		PrintWriter pw=null;
		

		try {
			pw=new PrintWriter(new FileWriter(file));
			
//			double sum=0;
//			for(int i=0;i<list.size();++i)
//			{
//				sum=sum+list.get(i).getX1()+list.get(i).getX2()+list.get(i).getX3();
//				if(i%1000==0)
//				{
//					System.out.println("i="+i+",sum="+sum);
//					pw.println(i+"\t"+sum);
//					sum=0;
//				}
//			}
			pw.println(list.get(i).getSeq()+"  "+list.get(i).getRf());
			for(CoupleUnit unit:coupleUnitList)
			{
				String line=unit.parse2String();
				pw.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			pw.close();
		}
		
	}

	/**
	 * 根据给定的数据集合，得到双元组建模数据集
	 * @param dataSet 选出来的相关数据元组集
	 * @param map PCV属性map
	 * @return 双元组建模数据集
	 */
	private static List<CoupleUnit> getCoupleUnitList(List<Node> dataSet, Map<Character, PCValue> map) {
		List<CoupleUnit> list=new ArrayList<CoupleUnit>();
		for(int i=0;i<dataSet.size();++i){
			for(int j=i+1;j<dataSet.size();++j){
				CoupleUnit unit=getCoupleUnit(dataSet.get(i),dataSet.get(j),map);
				list.add(unit);
			}
		}
		return list;
	}

	/**
	 * 根据给定的两个节点，建立双元组单元
	 * @param node1 第一个node
	 * @param node2 第二个node
	 * @param map 
	 * @return 建立好的双元组单元
	 */
	private static CoupleUnit getCoupleUnit(Node node1, Node node2, Map<Character, PCValue> map) {
		CoupleUnit unit=new CoupleUnit();
		unit.setSeqA(node1.getSeq());
		unit.setSeqB(node2.getSeq());
		unit.setRfA(node1.getRf());
		unit.setRfB(node2.getRf());
		//TODO  双元组单元m1-m3
		{
		PCValue v1=map.get(unit.getSeqA().charAt(0));
		PCValue v2=map.get(unit.getSeqB().charAt(0));
		double m1=Math.abs(v1.getH()-v2.getH())+Math.abs(v1.getNcisc()-v2.getNcisc())+Math.abs(v1.getP1()-v2.getP1())
				+Math.abs(v1.getP2()-v2.getP2())+Math.abs(v1.getSasa()-v2.getSasa())+Math.abs(v1.getVsc()-v2.getVsc());
		unit.setM1(m1);
		}
		{
		PCValue v1=map.get(unit.getSeqA().charAt(1));
		PCValue v2=map.get(unit.getSeqB().charAt(1));
		double m2=Math.abs(v1.getH()-v2.getH())+Math.abs(v1.getNcisc()-v2.getNcisc())+Math.abs(v1.getP1()-v2.getP1())
				+Math.abs(v1.getP2()-v2.getP2())+Math.abs(v1.getSasa()-v2.getSasa())+Math.abs(v1.getVsc()-v2.getVsc());
		unit.setM2(m2);
		}
		{
			PCValue v1=map.get(unit.getSeqA().charAt(2));
			PCValue v2=map.get(unit.getSeqB().charAt(2));
			double m3=Math.abs(v1.getH()-v2.getH())+Math.abs(v1.getNcisc()-v2.getNcisc())+Math.abs(v1.getP1()-v2.getP1())
					+Math.abs(v1.getP2()-v2.getP2())+Math.abs(v1.getSasa()-v2.getSasa())+Math.abs(v1.getVsc()-v2.getVsc());
			unit.setM3(m3);
			}
		unit.setY(Math.abs(unit.getRfA()-unit.getRfB()));
		return unit;
	}

	/**
	 * 对于输入的一个node，输出其相关的数据集合
	 * @param index  需要预测的node index
	 * @param list 所有数据集合
	 * @return  与此node相关的数据集合
	 */
	private static List<Node> getDataSet(int index, List<Node> list) {
		List<Node> dataSet=new ArrayList<Node>();
		List<UnitSigmas> listTemp=new ArrayList<UnitSigmas>();
		for(int i=0;i<list.size();++i){
			if(i!=index){
				UnitSigmas unit=getUnit(list.get(index),list.get(i));
				if((unit.getX1()+unit.getX2()+unit.getX3())>=1.0){
					dataSet.add(list.get(i));
					listTemp.add(unit);
				}
			}
		}
		Collections.sort(listTemp, new Comparator<UnitSigmas>() {
            public int compare(UnitSigmas arg0, UnitSigmas arg1) {
                return arg0.getY().compareTo(arg1.getY());
            }
        });
		
		//String outfilename="d:"+index+".txt";
		//writeData(listTemp,outfilename);
		System.out.println("relative dataSet size is " +dataSet.size());
		return dataSet;
	}

	/**
	 * 对于输入的一个seq，得到其相关的数据集合
	 * @param index  需要预测的seq
	 * @param list 所有数据集合
	 * @return  与此node相关的数据集合
	 */
	private static List<Node> getDataSet_All(String seq, List<Node> list) {
		List<Node> dataSet=new ArrayList<Node>();
		for(int i=0;i<list.size();++i){
			Node node=list.get(i);
			if(!(node.getSeq().equals(seq))){
				double xs[]=new double[3]; 
				for(int j=0;j<3;++j)
				{
					char A=seq.charAt(j);
					char B=node.getSeq().charAt(j);
					xs[j]=getdist(A,B);
				}
				if((xs[0]+xs[1]+xs[2])>=1.0){
					dataSet.add(node);
				}
			}
		}
		System.out.println("seq is:\t"+seq+"\t"+"relative dataSet size is:\t" +dataSet.size());
		
		
		return dataSet;
	}
	
	private static void testDataset_All(List<Node> list){
		String outname="d:alldatasetsize.txt";
		File file=new File(outname);
		PrintWriter pw=null;
		try {
			pw=new PrintWriter(new FileWriter(file));
		
			String aa="RHKDESTNQCGPAILMFWYV";
			int i=0,j=0,k=0;
			for(i=0;i<20;i++)
				for(j=0;j<20;j++)
					for(k=0;k<20;k++)
					{
						String seq=String.valueOf(aa.charAt(i))+String.valueOf(aa.charAt(j))+String.valueOf(aa.charAt(k));
						List <Node> dataSet=getDataSet_All(seq, list);
						pw.println("seq is:\t"+seq+"\t"+"relative dataSet size is:\t" +dataSet.size());
					}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				pw.close();
			}
		}
}
