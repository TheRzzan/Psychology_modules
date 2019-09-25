package com.morozov.experiments.fragments.cards.mvp.model

data class ExperimentModel(val title: String, val description: String,
                           val question: String, val variants: List<String>, val conclusion: String)