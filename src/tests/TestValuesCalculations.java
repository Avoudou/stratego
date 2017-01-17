package tests;

import gameLogic.SystemsManager;
import aiPack.StrategoRules;

public class TestValuesCalculations {
	public static void main(String[] args) {
		StrategoRules rules = new StrategoRules(new SystemsManager());

		System.out.println(rules.calculateTottalStr());
	}
}
