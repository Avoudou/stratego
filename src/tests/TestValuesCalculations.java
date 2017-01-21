package tests;

import project.stratego.ai.mcts.aiPack.StrategoRules;
import project.stratego.ai.mcts.gameLogic.SystemsManager;

public class TestValuesCalculations {
	public static void main(String[] args) {
		StrategoRules rules = new StrategoRules(new SystemsManager());

		System.out.println(rules.calculateTottalStr());
	}
}
