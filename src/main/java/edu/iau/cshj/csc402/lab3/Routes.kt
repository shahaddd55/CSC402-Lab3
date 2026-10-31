package edu.iau.cshj.csc402.lab3.navigation

import kotlinx.serialization.Serializable

@Serializable
object PlannerHome

@Serializable
data class TaskDetail(val title: String)