package com.example.pygtianguistraker.data.model

class TianguisCollection : ArrayList<Tianguis>()
data class Tianguis(
    val nombreTianguis: String,
    val direccionTianguis: String,
    val horarioLunesTianguis: String,
    val horarioMartesTianguis: String,
    val horarioMiercolesTianguis: String,
    val horarioJuevesTianguis: String,
    val horarioViernesTianguis: String,
    val horarioSabadoTianguis: String,
    val horarioDomingoTianguis: String
)
