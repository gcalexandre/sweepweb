package com.alexandrecasrtilho.websweep.util;

import java.util.HashMap;
import java.util.Map;

public class HashMapOrthMat {
	
	static private Map<String, String> listMapOrth = null;

	static private void init() {

		// MapKey orth_mat

		listMapOrth = new HashMap<String, String>();

		listMapOrth.put("1,1,3600", new String("/media/list_orthmat/orthMat_113_AA_600.sweepproj"));
		listMapOrth.put("1,1,3900", new String("/media/list_orthmat/orthMat_113_AA_900.sweepproj"));
		listMapOrth.put("1,1,31200", new String("/media/list_orthmat/orthMat_113_AA_1200.sweepproj"));
		listMapOrth.put("1,1,31500", new String("/media/list_orthmat/orthMat_113_AA_1500.sweepproj"));
		listMapOrth.put("1,1,31800", new String("/media/list_orthmat/orthMat_113_AA_1800.sweepproj"));
		listMapOrth.put("1,1,32100", new String("/media/list_orthmat/orthMat_113_AA_2100.sweepproj"));
		listMapOrth.put("2,0,2600", new String("/media/list_orthmat/orthMat_202_AA_600.sweepproj"));
		listMapOrth.put("2,0,2900", new String("/media/list_orthmat/orthMat_202_AA_900.sweepproj"));
		listMapOrth.put("2,0,21200", new String("/media/list_orthmat/orthMat_202_AA_1200.sweepproj"));
		listMapOrth.put("2,0,21500", new String("/media/list_orthmat/orthMat_202_AA_1500.sweepproj"));
		listMapOrth.put("2,0,21800", new String("/media/list_orthmat/orthMat_202_AA_1800.sweepproj"));
		listMapOrth.put("2,0,22100", new String("/media/list_orthmat/orthMat_202_AA_2100.sweepproj"));
		listMapOrth.put("2,1,2600", new String("/media/list_orthmat/orthMat_212_AA_600.sweepproj"));
		listMapOrth.put("2,1,2900", new String("/media/list_orthmat/orthMat_212_AA_900.sweepproj"));
		listMapOrth.put("2,1,21200", new String("/media/list_orthmat/orthMat_212_AA_1200.sweepproj"));
		listMapOrth.put("2,1,21500", new String("/media/list_orthmat/orthMat_212_AA_1500.sweepproj"));
		listMapOrth.put("2,1,21800", new String("/media/list_orthmat/orthMat_212_AA_1800.sweepproj"));
		listMapOrth.put("2,1,22100", new String("/media/list_orthmat/orthMat_212_AA_2100.sweepproj"));
		listMapOrth.put("2,0,3600", new String("/media/list_orthmat/orthMat_203_AA_600.sweepproj"));
		listMapOrth.put("2,0,3900", new String("/media/list_orthmat/orthMat_203_AA_900.sweepproj"));
		listMapOrth.put("2,0,31200", new String("/media/list_orthmat/orthMat_203_AA_1200.sweepproj"));
		listMapOrth.put("2,0,31500", new String("/media/list_orthmat/orthMat_203_AA_1500.sweepproj"));
		listMapOrth.put("2,0,31800", new String("/media/list_orthmat/orthMat_203_AA_1800.sweepproj"));
		listMapOrth.put("2,0,32100", new String("/media/list_orthmat/orthMat_203_AA_2100.sweepproj"));
		listMapOrth.put("3,0,1600", new String("/media/list_orthmat/orthMat_301_AA_600.sweepproj"));
		listMapOrth.put("3,0,1900", new String("/media/list_orthmat/orthMat_301_AA_900.sweepproj"));
		listMapOrth.put("3,0,11200", new String("/media/list_orthmat/orthMat_301_AA_1200.sweepproj"));
		listMapOrth.put("3,0,11500", new String("/media/list_orthmat/orthMat_301_AA_1500.sweepproj"));
		listMapOrth.put("3,0,11800", new String("/media/list_orthmat/orthMat_301_AA_1800.sweepproj"));
		listMapOrth.put("3,0,12100", new String("/media/list_orthmat/orthMat_301_AA_2100.sweepproj"));
		listMapOrth.put("3,0,2600", new String("/media/list_orthmat/orthMat_302_AA_600.sweepproj"));
		listMapOrth.put("3,0,2900", new String("/media/list_orthmat/orthMat_302_AA_900.sweepproj"));
		listMapOrth.put("3,0,21200", new String("/media/list_orthmat/orthMat_302_AA_1200.sweepproj"));
		listMapOrth.put("3,0,21500", new String("/media/list_orthmat/orthMat_302_AA_1500.sweepproj"));
		listMapOrth.put("3,0,21800", new String("/media/list_orthmat/orthMat_302_AA_1800.sweepproj"));
		listMapOrth.put("3,0,22100", new String("/media/list_orthmat/orthMat_302_AA_2100.sweepproj"));
		listMapOrth.put("3,1,1600", new String("/media/list_orthmat/orthMat_311_AA_600.sweepproj"));
		listMapOrth.put("3,1,1900", new String("/media/list_orthmat/orthMat_311_AA_900.sweepproj"));
		listMapOrth.put("3,1,11200", new String("/media/list_orthmat/orthMat_311_AA_1200.sweepproj"));
		listMapOrth.put("3,1,11500", new String("/media/list_orthmat/orthMat_311_AA_1500.sweepproj"));
		listMapOrth.put("3,1,11800", new String("/media/list_orthmat/orthMat_311_AA_1800.sweepproj"));
		listMapOrth.put("3,1,12100", new String("/media/list_orthmat/orthMat_311_AA_2100.sweepproj"));
		listMapOrth.put("4,7,4600", new String("/media/list_orthmat/orthMat_474_NT_600.sweepproj"));
		listMapOrth.put("4,7,4900", new String("/media/list_orthmat/orthMat_474_NT_900.sweepproj"));
		listMapOrth.put("4,7,41200", new String("/media/list_orthmat/orthMat_474_NT_1200.sweepproj"));
		listMapOrth.put("4,7,41500", new String("/media/list_orthmat/orthMat_474_NT_1500.sweepproj"));
		listMapOrth.put("4,7,41800", new String("/media/list_orthmat/orthMat_474_NT_1800.sweepproj"));
		listMapOrth.put("4,7,42100", new String("/media/list_orthmat/orthMat_474_NT_2100.sweepproj"));
		listMapOrth.put("1,1,3100", new String("/media/list_orthmat/orthMat_113_AA_100.sweepproj"));
		listMapOrth.put("1,1,3200", new String("/media/list_orthmat/orthMat_113_AA_200.sweepproj"));
		listMapOrth.put("1,1,3400", new String("/media/list_orthmat/orthMat_113_AA_400.sweepproj"));
		listMapOrth.put("1,1,32400", new String("/media/list_orthmat/orthMat_113_AA_2400.sweepproj"));
		listMapOrth.put("1,1,32700", new String("/media/list_orthmat/orthMat_113_AA_2700.sweepproj"));
		listMapOrth.put("1,1,33000", new String("/media/list_orthmat/orthMat_113_AA_3000.sweepproj"));
		listMapOrth.put("2,0,2100", new String("/media/list_orthmat/orthMat_202_AA_100.sweepproj"));
		listMapOrth.put("2,0,2200", new String("/media/list_orthmat/orthMat_202_AA_200.sweepproj"));
		listMapOrth.put("2,0,2400", new String("/media/list_orthmat/orthMat_202_AA_400.sweepproj"));
		listMapOrth.put("2,0,22400", new String("/media/list_orthmat/orthMat_202_AA_2400.sweepproj"));
		listMapOrth.put("2,0,22700", new String("/media/list_orthmat/orthMat_202_AA_2700.sweepproj"));
		listMapOrth.put("2,0,23000", new String("/media/list_orthmat/orthMat_202_AA_3000.sweepproj"));
		listMapOrth.put("2,1,2100", new String("/media/list_orthmat/orthMat_212_AA_100.sweepproj"));
		listMapOrth.put("2,1,2200", new String("/media/list_orthmat/orthMat_212_AA_200.sweepproj"));
		listMapOrth.put("2,1,2400", new String("/media/list_orthmat/orthMat_212_AA_400.sweepproj"));
		listMapOrth.put("2,1,22400", new String("/media/list_orthmat/orthMat_212_AA_2400.sweepproj"));
		listMapOrth.put("2,1,22700", new String("/media/list_orthmat/orthMat_212_AA_2700.sweepproj"));
		listMapOrth.put("2,1,23000", new String("/media/list_orthmat/orthMat_212_AA_3000.sweepproj"));
		listMapOrth.put("2,0,3100", new String("/media/list_orthmat/orthMat_203_AA_100.sweepproj"));
		listMapOrth.put("2,0,3200", new String("/media/list_orthmat/orthMat_203_AA_200.sweepproj"));
		listMapOrth.put("2,0,3400", new String("/media/list_orthmat/orthMat_203_AA_400.sweepproj"));
		listMapOrth.put("2,0,32400", new String("/media/list_orthmat/orthMat_203_AA_2400.sweepproj"));
		listMapOrth.put("2,0,32700", new String("/media/list_orthmat/orthMat_203_AA_2700.sweepproj"));
		listMapOrth.put("2,0,33000", new String("/media/list_orthmat/orthMat_203_AA_3000.sweepproj"));
		listMapOrth.put("3,0,1100", new String("/media/list_orthmat/orthMat_301_AA_100.sweepproj"));
		listMapOrth.put("3,0,1200", new String("/media/list_orthmat/orthMat_301_AA_200.sweepproj"));
		listMapOrth.put("3,0,1400", new String("/media/list_orthmat/orthMat_301_AA_400.sweepproj"));
		listMapOrth.put("3,0,12400", new String("/media/list_orthmat/orthMat_301_AA_2400.sweepproj"));
		listMapOrth.put("3,0,12700", new String("/media/list_orthmat/orthMat_301_AA_2700.sweepproj"));
		listMapOrth.put("3,0,13000", new String("/media/list_orthmat/orthMat_301_AA_3000.sweepproj"));
		listMapOrth.put("3,0,2100", new String("/media/list_orthmat/orthMat_302_AA_100.sweepproj"));
		listMapOrth.put("3,0,2200", new String("/media/list_orthmat/orthMat_302_AA_200.sweepproj"));
		listMapOrth.put("3,0,2400", new String("/media/list_orthmat/orthMat_302_AA_400.sweepproj"));
		listMapOrth.put("3,0,22400", new String("/media/list_orthmat/orthMat_302_AA_2400.sweepproj"));
		listMapOrth.put("3,0,22700", new String("/media/list_orthmat/orthMat_302_AA_2700.sweepproj"));
		listMapOrth.put("3,0,23000", new String("/media/list_orthmat/orthMat_302_AA_3000.sweepproj"));
		listMapOrth.put("3,1,1100", new String("/media/list_orthmat/orthMat_311_AA_100.sweepproj"));
		listMapOrth.put("3,1,1200", new String("/media/list_orthmat/orthMat_311_AA_200.sweepproj"));
		listMapOrth.put("3,1,1400", new String("/media/list_orthmat/orthMat_311_AA_400.sweepproj"));
		listMapOrth.put("3,1,12400", new String("/media/list_orthmat/orthMat_311_AA_2400.sweepproj"));
		listMapOrth.put("3,1,12700", new String("/media/list_orthmat/orthMat_311_AA_2700.sweepproj"));
		listMapOrth.put("3,1,13000", new String("/media/list_orthmat/orthMat_311_AA_3000.sweepproj"));
		listMapOrth.put("4,7,4100", new String("/media/list_orthmat/orthMat_474_NT_100.sweepproj"));
		listMapOrth.put("4,7,4200", new String("/media/list_orthmat/orthMat_474_NT_200.sweepproj"));
		listMapOrth.put("4,7,4400", new String("/media/list_orthmat/orthMat_474_NT_400.sweepproj"));
		listMapOrth.put("4,7,42400", new String("/media/list_orthmat/orthMat_474_NT_2400.sweepproj"));
		listMapOrth.put("4,7,42700", new String("/media/list_orthmat/orthMat_474_NT_2700.sweepproj"));
		listMapOrth.put("4,7,43000", new String("/media/list_orthmat/orthMat_474_NT_3000.sweepproj"));

	}

	static public synchronized Map<String, String> getHashMap() {

		if (listMapOrth == null) {
			init();
		}
		return listMapOrth;
	}

}
