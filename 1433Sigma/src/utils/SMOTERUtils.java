package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import beans.UnitSigmas;

public class SMOTERUtils {
	/**
	 * �������ݼ��Լ���ֵ������SMOTER�����ϳ�������
	 * 
	 * @param dataSet
	 *            ԭʼ���ݼ�
	 * @param tempArray
	 *            ��Ҫ���Ե�����
	 * @param dem
	 *            ����ά��
	 * @param threshold
	 *            ��ֵ
	 * @param over
	 *            �����ñ�����100�� %over ���һ�������������5������5����over=500
	 * @param under
	 *            Ƿ��������
	 * @param k
	 *            knn���� k
	 * @return �µ����ݼ���
	 */
	public static List<Double[]> getSmoteR(List<Double[]> dataSet,
			Double[] tempArray, int dem, Double threshold, int over, int under,
			int k) {
		if (dataSet == null || dataSet.size() == 0)
			return null;
		Collections.sort(dataSet, new Comparator<Double[]>() {
			@Override
			public int compare(Double[] arg0, Double[] arg1) {
				// TODO Auto-generated method stub
				return arg0[arg0.length - 1].compareTo(arg1[arg1.length - 1]);
			}
		});
		dataSet = propertyToOne(dataSet, tempArray, dem);
		tempArray = dataSet.get(0);
		dataSet.remove(0);
		List<Double[]> rareList = new ArrayList<Double[]>();
		List<Double[]> normList = new ArrayList<Double[]>();
		int length = dataSet.get(0).length;
		for (int i = 0; i < dataSet.size(); ++i) {
			if (dataSet.get(i)[length - 1] >= threshold)
				rareList.add(dataSet.get(i));
			else
				normList.add(dataSet.get(i));
		}
		
//		//δ���Ƿ��������  smote
//		List<Double[]> newrareList = genSynthCases(rareList, dem, over / 100, k);
//		List<Double[]> resultList = new ArrayList<Double[]>();
//		resultList.add(tempArray);
//		resultList.addAll(rareList);
//		resultList.addAll(newrareList);
//		resultList.addAll(normList);

		
		//���Ƿ���ó���  under+smote
		List<Double[]> newrareList = genSynthCases(rareList, dem, over / 100, k);
		List<Double[]> resultList = new ArrayList<Double[]>();
		int allRare = rareList.size() + newrareList.size();
		// System.out.println(normList.size()+"  allRare="+
		// allRare+" rare size="+rareList.size());
		List<Double[]> tempnormList = new ArrayList<Double[]>();
		tempnormList.addAll(normList);
		List<Double[]> newnormList = underSmote(tempnormList, under, allRare);
		// System.out.println(normList.size()+"  allRare="+ allRare);
		resultList.add(tempArray);
		resultList.addAll(rareList);
		resultList.addAll(newrareList);
		resultList.addAll(newnormList);
		return resultList;

	}

	/**
	 * Ƿ��������������
	 * 
	 * @param normList
	 *            ԭ������������
	 * @param under
	 *            Ƿ�����ı��� ���� 50��ʾǷ��������ϡ��������50%
	 * @param allRare
	 *            ����ϡ����������Ŀ
	 * @return �µ�Ƿ��������������
	 */
	private static List<Double[]> underSmote(List<Double[]> normList,
			int under, int allRare) {
		List<Double[]> newnormList = new ArrayList<Double[]>();
		int size = (int) Math.round((under * 1.0 / 100) * allRare);
		if(allRare<=50) return normList;
		if (size >= normList.size())
			size = normList.size();
		//System.out.println("new norm size=" + size + "normList size="+ normList.size());
		for (int i = 0; i < size; ++i) {
			Random random = new Random();
			int randomx = random.nextInt();
			randomx = Math.abs(randomx % normList.size());
			newnormList.add(normList.get(randomx));
			normList.remove(randomx);
		}
		return newnormList;
	}

	/**
	 * ���ʹ�һ��
	 * 
	 * @param dataSet
	 *            ���ݼ�
	 * @param tempArray
	 *            ��Ҫ���Ե�����
	 * @param dem
	 *            ���ݼ���ά��
	 * @return��һ��֮������ݼ�
	 */
	private static List<Double[]> propertyToOne(List<Double[]> dataSet,
			Double[] tempArray, int dem) {
		// ��ʼ�������С����
		Double[] max = new Double[dem];
		Double[] min = new Double[dem];
		for (int i = 0; i < dem; ++i) {
			max[i] = -99999.0;
			min[i] = 99999.0;
		}
		// ����ÿ��ά�ȵ��������Сֵ
		for (int i = 0; i < dataSet.size(); ++i) {
			Double[] temp = dataSet.get(i);
			for (int j = 0; j < dem; ++j) {
				if (temp[j] > max[j])
					max[j] = temp[j];
				if (temp[j] < min[j])
					min[j] = temp[j];
			}
		}
		for (int j = 0; j < dem; ++j) {
			if (tempArray[j] > max[j])
				max[j] = tempArray[j];
			if (tempArray[j] < min[j])
				min[j] = tempArray[j];
		}
		// �����µĹ�һ��������ݼ�
		List<Double[]> newDataSet = new ArrayList<Double[]>();
		for (int j = 0; j < dem; ++j) {
			tempArray[j] = (tempArray[j] - min[j]) / (max[j] - min[j]);
		}
		newDataSet.add(tempArray);
		for (int i = 0; i < dataSet.size(); ++i) {
			Double[] temp = dataSet.get(i);
			for (int j = 0; j < dem; ++j) {
				temp[j] = (temp[j] - min[j]) / (max[j] - min[j]);
			}
			newDataSet.add(temp);
		}
		return newDataSet;
	}

	private static List<Double[]> genSynthCases(List<Double[]> rareList,
			int dem, int ng, int k) {
		List<Double[]> newrareList = new ArrayList<Double[]>();
		for (int i = 0; i < rareList.size(); ++i) {
			List<Double[]> nns = KNNUtils.getKnn(rareList, dem, k, i);
			for (int j = 0; j < ng; j++) {
				Random random = new Random();
				int randomx = random.nextInt();
				randomx = Math.abs(randomx % k);
				if (randomx > (nns.size() - 1))
					randomx = (nns.size() - 1);
				Double[] x = nns.get(randomx);
				Double[] c = rareList.get(i);
				Double[] newCase = new Double[c.length];
				for (int d = 0; d < dem; d++) {
					double diff = c[d] - x[d];
					newCase[d] = x[d] + Math.random() * diff;
				}// �ϳ�ÿһ��ά��

				double d1 = getDist(newCase, c);
				double d2 = getDist(newCase, x);
				newCase[dem] = (d2 * c[dem] + d1 * x[dem]) / (d1 + d2);
				newrareList.add(newCase);
			} // ����ng������
		} // ��ÿһ�����ݽ��кϳ�����
		return newrareList;
	}

	private static double getDist(Double[] newCase, Double[] c) {
		Double dist = 0.0;
		for (int j = 0; j < (newCase.length - 1); ++j) {
			dist += Math.pow((newCase[j] - c[j]), 2);
		}
		dist = Math.sqrt(dist);
		return dist;
	}
}
