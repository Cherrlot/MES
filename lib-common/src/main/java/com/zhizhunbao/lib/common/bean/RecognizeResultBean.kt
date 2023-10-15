package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by xwh on 2020/9/16  星期三
 * description:菜品切图分析信息bean
 */
@Parcelize
data class RecognizeResultBean(
    var originImageUrl: String?,
    var recognizeId: String,
    var libraryId: String?,
    var picWidth: Int? = 0,
    var picHeight: Int? = 0,
    var items: MutableList<ItemBean>
) : Parcelable {
    @Parcelize
    data class ItemBean(
        var index: Int,
        var totalVote: Int,
        var distance: Boolean,//大概率出错的菜品
        var subImage: SubImageBean?,
        var similarList: ArrayList<SimilarBean>
    ) : Parcelable {
        @Parcelize
        data class SubImageBean(
            var location: LocationBean?
        ) : Parcelable {
            @Parcelize
            data class LocationBean(
                var width: Int,
                var top: Int,
                var left: Int,
                var height: Int
            ) : Parcelable
        }

        @Parcelize
        data class SimilarBean(
            var id: String?,
            var name: String,
            var label: String,
            var price: Double,
            var status: Int,
            var probability: String?,
            var coverImageUrl: String?,
            var type: TypeBean? = null,
            var validForRecognition: Boolean?
        ) : Parcelable {
            fun getProbability(): Double {
                if (probability.isNullOrBlank()) {
                    return 0.00
                }
                return probability!!.toDouble()
            }
        }
    }

    @Parcelize
    data class TypeBean(
        var id: String?,
        var name: String?,
    ) : Parcelable
}