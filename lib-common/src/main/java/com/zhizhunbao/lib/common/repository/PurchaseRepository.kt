package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.online.OnlinePurchaseWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 采购管理Repository
 */
class PurchaseRepository(
    private val onlinePurchaseWebService: OnlinePurchaseWebService
) {

    /**
     * 采购管理列表
     */
    suspend fun getPurchaseList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getPurchaseList(map)
    }

    /**
     * 采购管理列表
     */
    suspend fun getPurchaseDetailList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getPurchaseDetailList(map)
    }

    /**
     * 采购数据自动生成
     */
    suspend fun autoCreatePurchase(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.autoCreatePurchase(map)
    }

    /**
     * 获取类型列表
     */
    suspend fun getTypeList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getTypeList(map)
    }

    /**
     * 获取商品列表
     */
    suspend fun getProductList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getProductList(map)
    }

    /**
     * 获取公司列表
     */
    suspend fun getCustomerList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getCustomerList(map)
    }

    /**
     * 获取供应商列表
     */
    suspend fun getProviderList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getProviderList(map)
    }

    /**
     * 获取公司详情
     */
    suspend fun getCustomerDetail(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getCustomerDetail(map)
    }

    /**
     * 采购管理添加
     */
    suspend fun addPurchase(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.addPurchase(request)
    }

    /**
     * 盘库
     */
    suspend fun productCheck(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.productCheck(request)
    }

    /**
     * 代客下单
     */
    suspend fun orderCreate(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.orderCreate(request)
    }

    /**
     * 采购管理编辑
     */
    suspend fun updatePurchase(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.updatePurchase(request)
    }

    /**
     * 查地址
     */
    suspend fun getAddressList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getAddressList(map)
    }

    /**
     * 查采购列表
     */
    suspend fun getMallPurchaseList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getMallPurchaseList(map)
    }

    /**
     * 获取原材料列表
     */
    suspend fun getMaterialList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getMaterialList(map)
    }

    /**
     * 获取商品详情
     */
    suspend fun getProduct(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getProduct(map)
    }

    /**
     * 订单列表
     */
    suspend fun getMallOrderList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getMallOrderList(map)
    }

    /**
     * 订单详情
     */
    suspend fun getMallOrderDetail(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.getMallOrderDetail(map)
    }

    /**
     * 采购列表新增
     */
    suspend fun purchaseMallCreate(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.purchaseMallCreate(request)
    }

    /**
     * 编辑公司
     */
    suspend fun updateCustomer(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.updateCustomer(request)
    }

    /**
     * 确认备货
     */
    suspend fun confirmPrepare(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.confirmPrepare(request)
    }

    /**
     * 删除公司
     */
    suspend fun deleteCustomer(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.deleteCustomer(request)
    }

    /**
     * 创建公司
     */
    suspend fun createCustomer(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.createCustomer(request)
    }

    /**
     * 编辑供应商
     */
    suspend fun updateSorting(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.updateSorting(request)
    }

    /**
     * 删除供应商
     */
    suspend fun deleteSorting(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.deleteSorting(request)
    }

    /**
     * 商品添加
     */
    suspend fun createProduct(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.createProduct(request)
    }
    /**
     * 商品修改
     */
    suspend fun updateProduct(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.updateProduct(request)
    }
    /**
     * 商品删除
     */
    suspend fun deleteProduct(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.deleteProduct(request)
    }

    /**
     * 获取类型添加
     */
    suspend fun createType(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.createType(request)
    }
    /**
     * 获取类型编辑
     */
    suspend fun updateType(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.updateType(request)
    }
    /**
     * 获取类型删除
     */
    suspend fun deleteType(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.deleteType(request)
    }
    /**
     * 创建供应商
     */
    suspend fun createSorting(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.createSorting(request)
    }

    /**
     * 分拣
     */
    suspend fun orderUpdate(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.orderUpdate(request)
    }

    /**
     * 采购完成
     */
    suspend fun buyMallUpdate(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.buyMallUpdate(request)
    }

    /**
     * 采购列表更新
     */
    suspend fun purchaseMallUpdate(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.purchaseMallUpdate(request)
    }

    /**
     * 拍照识别原材料
     */
    suspend fun purchaseRecognize(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.purchaseRecognize(request)
    }

    /**
     * 采购管理删除
     */
    suspend fun deletePurchase(request: RequestBody) = withContext(Dispatchers.IO) {
        onlinePurchaseWebService.deletePurchase(request)
    }

}