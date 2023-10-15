package com.zhizhunbao.lib.common.net.service.local

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.*
import com.zhizhunbao.lib.common.net.constant.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * 本地菜品接口地址
 */
interface LocalDishWebService {

    /**
     * 获取拍照菜品分析信息
     */
    @POST(RECOGNIZE_URL)
    suspend fun getRecognizeData(@Body request: RequestBody): BaseResponse<RecognizeResultBean>

    /**
     * 获取餐厅信息
     */
    @GET(DINING_INFO_URL)
    suspend fun getDiningInfo(): BaseResponse<DiningListBean>

    /**
     * 获取餐厅列表
     */
    @GET
    suspend fun getDiningList(@Url url: String, @Query("CompanyID") CompanyID: String): BaseResponse<MutableList<DiningListBean>>
    /**
     * 一键上下架菜品
     */
    @POST(DISH_PUT_AWAY_URL)
    suspend fun setDishUpOrDown(@Body request: RequestBody): BaseResponse<Any?>
    /**
     * 获取餐厅菜品
     */
    @GET(DISH_LIST_URL)
    suspend fun getDishList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<DishBean>>

    /**
     * 移除菜品
     */
    @POST(DISH_REMOVE_URL)
    suspend fun setDishRemove(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 新增菜品
     */
    @POST(DISH_CREATE_URL)
    suspend fun setDishCreate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 编辑菜品
     */
    @POST(DISH_MODIFY_URL)
    suspend fun setDishModify(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 菜品分类管理列表数据
     */
    @GET(DISH_TYPE_MANAGER_URL)
    suspend fun getDishManagerTypeList(@Query("diningId") diningRoomId: String): BaseResponse<MutableList<DishManagerTypeBean>>

    /**
     * 获取标准菜
     */
    @GET(DISH_STANDARD_NAME_URL)
    suspend fun getStandardDishByName(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<StandardDishBean>?>

    /**
     * 菜品分类管理排序
     */
    @POST(SORT_DISH_TYPE_URL)
    suspend fun sortDishType(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 修改菜品排序
     */
    @POST(MODIFY_DISH_SORT_URL)
    suspend fun modifyDishSort(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 菜品分类删除
     */
    @POST(DELETE_DISH_TYPE_URL)
    suspend fun deleteDishType(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 菜品分类创建
     */
    @POST(CREATE_DISH_TYPE_URL)
    suspend fun createDishType(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 修改菜品分类
     */
    @POST(MODIFY_DISH_TYPE_URL)
    suspend fun modifyDishType(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 上传图片
     */
    @Multipart
    @POST
    suspend fun upload(@Url url: String,
        @Part part: MultipartBody.Part, @Part("dir") dir: String? = null, @Part("keyId") keyId: String,
        @Part("type") type: Int, @Part("metadata") metadata: String? = null
    ): BaseResponse<UploadPictureBean>
}