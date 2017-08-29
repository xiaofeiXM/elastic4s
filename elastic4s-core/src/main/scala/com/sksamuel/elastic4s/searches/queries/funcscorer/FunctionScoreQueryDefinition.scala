package com.sksamuel.elastic4s.searches.queries.funcscorer

import com.sksamuel.elastic4s.searches.queries.QueryDefinition

case class FunctionScoreQueryDefinition(query: Option[QueryDefinition] = None,
                                        scorers: Seq[ScoreFunctionDefinition] = Nil,
                                        boost: Option[Double] = None,
                                        maxBoost: Option[Double] = None,
                                        minScore: Option[Double] = None,
                                        scoreMode: Option[FunctionScoreQueryScoreMode] = None,
                                        boostMode: Option[CombineFunction] = None,
                                        scriptScore: Option[ScriptScoreDefinition] = None) extends QueryDefinition {

  def boost(boost: Double): FunctionScoreQueryDefinition = copy(boost = Option(boost))
  def minScore(min: Double): FunctionScoreQueryDefinition = copy(minScore = Option(min))
  def maxBoost(maxBoost: Double): FunctionScoreQueryDefinition = copy(maxBoost = Option(maxBoost))

  def scoreMode(mode: String): FunctionScoreQueryDefinition = scoreMode(FunctionScoreQueryScoreMode.valueOf(mode.toUpperCase))
  def scoreMode(mode: FunctionScoreQueryScoreMode): FunctionScoreQueryDefinition = copy(scoreMode = Some(mode))

  def boostMode(mode: String): FunctionScoreQueryDefinition = boostMode(CombineFunction.valueOf(mode.toUpperCase))
  def boostMode(mode: CombineFunction): FunctionScoreQueryDefinition = copy(boostMode = Some(mode))

  def scriptScore(script: ScriptScoreDefinition): FunctionScoreQueryDefinition = copy(scriptScore = Some(script))

  def query(query: QueryDefinition): FunctionScoreQueryDefinition = copy(query = Some(query))

  def scorers(first: ScoreFunctionDefinition,
              rest: ScoreFunctionDefinition*): FunctionScoreQueryDefinition = scorers(first +: rest)

  def scorers(scorers: Iterable[ScoreFunctionDefinition]): FunctionScoreQueryDefinition =
    scoreFuncs(scorers)

  def scoreFuncs(first: ScoreFunctionDefinition,
                 rest: ScoreFunctionDefinition*): FunctionScoreQueryDefinition = scoreFuncs(first +: rest)

  def scoreFuncs(functions: Iterable[ScoreFunctionDefinition]): FunctionScoreQueryDefinition = copy(scorers = functions.toSeq)
}
