package com.zhizhunbao.lib.common.net.service.online

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.CompanyBean
import com.zhizhunbao.lib.common.bean.MaterialBean
import com.zhizhunbao.lib.common.bean.OrderBean
import com.zhizhunbao.lib.common.bean.ProductBean
import com.zhizhunbao.lib.common.bean.ProviderBean
import com.zhizhunbao.lib.common.bean.PurchaseAutoBean
import com.zhizhunbao.lib.common.bean.PurchaseBean
import com.zhizhunbao.lib.common.bean.TypeBean
import com.zhizhunbao.lib.common.net.constant.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * 采购管理接口地址
 */
interface OnlinePurchaseWebService {

    /**
     * 采购管理列表
     */
    @GET(PURCHASE_LIST_URL)
    suspend fun getPurchaseList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<PurchaseBean>?>

    /**
     * 采购管理列表
     */
    @GET(PURCHASE_DETAIL_LIST_URL)
    suspend fun getPurchaseDetailList(@QueryMap map: HashMap<String, Any?>): BaseResponse<PurchaseBean?>

    /**
     * 采购数据自动生成
     */
    @GET(PURCHASE_AUTO_CREATE_URL)
    suspend fun autoCreatePurchase(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<PurchaseAutoBean>?>

    /**
     * 获取类型列表
     */
    @GET(TYPE_LIST_URL)
    suspend fun getTypeList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<TypeBean>?>

    /**
     * 获取商品列表
     */
    @GET(PRODUCT_LIST_URL)
    suspend fun getProductList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<ProductBean>?>

    /**
     * 获取公司列表
     */
    @GET(CUSTOMER_LIST_URL)
    suspend fun getCustomerList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<CompanyBean>?>

    /**
     * 获取供应商列表
     */
    @GET(SORTING_LIST_URL)
    suspend fun getProviderList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<ProviderBean>?>

    /**
     * 获取公司详情
     */
    @GET(CUSTOMER_DETAIL_URL)
    suspend fun getCustomerDetail(@QueryMap map: HashMap<String, Any?>): BaseResponse<CompanyBean?>

    /**
     * 获取原材料列表
     */
    @GET(PURCHASE_MATERIAL_URL)
    suspend fun getMaterialList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<MaterialBean>?>

    /**
     * 获取商品详情
     */
    @GET(PRODUCT_DETAIL_URL)
    suspend fun getProduct(@QueryMap map: HashMap<String, Any?>): BaseResponse<ProductBean?>

    /**
     * 查地址
     */
    @GET(ADDRESS_LIST_URL)
    suspend fun getAddressList(@QueryMap map: HashMap<String, Any?>): BaseResponse<CompanyBean?>

    /**
     * 查采购列表
     */
    @GET(MALL_PURCHASE_LIST_URL)
    suspend fun getMallPurchaseList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<MaterialBean>?>

    /**
     * 订单列表
     */
    @GET(MALL_ORDER_LIST_URL)
    suspend fun getMallOrderList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<OrderBean>?>

    /**
     * 订单详情
     */
    @GET(MALL_ORDER_DETAIL_URL)
    suspend fun getMallOrderDetail(@QueryMap map: HashMap<String, Any?>): BaseResponse<OrderBean?>

    /**
     * 创建公司
     */
    @POST(CUSTOMER_CREATE_URL)
    suspend fun createCustomer(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 编辑公司
     */
    @POST(CUSTOMER_UPDATE_URL)
    suspend fun updateCustomer(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 商品添加
     */
    @POST(PRODUCT_CREATE_URL)
    suspend fun createProduct(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 商品修改
     */
    @POST(PRODUCT_UPDATE_URL)
    suspend fun updateProduct(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 商品删除
     */
    @POST(PRODUCT_DELETE_URL)
    suspend fun deleteProduct(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 获取类型添加
     */
    @POST(TYPE_LIST_CREATE_URL)
    suspend fun createType(@Body request: RequestBody): BaseResponse<TypeBean?>

    /**
     * 获取类型编辑
     */
    @POST(TYPE_LIST_UPDATE_URL)
    suspend fun updateType(@Body request: RequestBody): BaseResponse<TypeBean?>

    /**
     * 获取类型删除
     */
    @POST(TYPE_LIST_DELETE_URL)
    suspend fun deleteType(@Body request: RequestBody): BaseResponse<TypeBean?>

    /**
     * 创建供应商
     */
    @POST(SORTING_CREATE_URL)
    suspend fun createSorting(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 编辑供应商
     */
    @POST(SORTING_UPDATE_URL)
    suspend fun updateSorting(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 删除供应商
     */
    @POST(SORTING_DELETE_URL)
    suspend fun deleteSorting(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 删除公司
     */
    @POST(CUSTOMER_DELETE_URL)
    suspend fun deleteCustomer(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 确认备货
     */
    @POST(MALL_PREPARE_URL)
    suspend fun confirmPrepare(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 分拣
     */
    @POST(MALL_ORDER_UPDATE_URL)
    suspend fun orderUpdate(@Body request: RequestBody): BaseResponse<MaterialBean?>

    /**
     * 采购列表新增
     */
    @POST(MALL_PURCHASE_CREATE_URL)
    suspend fun purchaseMallCreate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 采购完成
     */
    @POST(MALL_BUY_URL)
    suspend fun buyMallUpdate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 采购列表更新
     */
    @POST(MALL_PURCHASE_UPDATE_URL)
    suspend fun purchaseMallUpdate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 拍照识别原材料
     */
    @POST(PURCHASE_MATERIAL_RECOGNIZE_URL)
    suspend fun purchaseRecognize(@Body request: RequestBody): BaseResponse<MaterialBean?>

    /**
     * 代客下单
     */
    @POST(ORDER_CREATE_URL)
    suspend fun orderCreate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 采购管理添加
     */
    @POST(PURCHASE_ADD_URL)
    suspend fun addPurchase(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 盘库
     */
    @POST(PRODUCT_CHECK_URL)
    suspend fun productCheck(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 采购管理编辑
     */
    @POST(PURCHASE_UPDATE_URL)
    suspend fun updatePurchase(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 采购管理删除
     */
    @POST(PURCHASE_DELETE_URL)
    suspend fun deletePurchase(@Body request: RequestBody): BaseResponse<Any?>

}