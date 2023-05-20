package org.d3if3115.aplikasikalkulatorkalori.model

import org.d3if3115.aplikasikalkulatorkalori.db.KaloriEntity

fun KaloriEntity.hitungKalori(): HasilKalori {
    var total = 0f
    val hasil: Float
    if (isMale) {
        tinggi = (6.25 * tinggi).toFloat()
        berat = 10f
        usia= 5
        total = 5 + tinggi + berat - usia
    } else {
        tinggi = (6.25 * tinggi).toFloat()
        berat = 10f
        usia= 5
        total = 5 + tinggi + berat - usia - 161f
    }
    return HasilKalori(total.toDouble())
}