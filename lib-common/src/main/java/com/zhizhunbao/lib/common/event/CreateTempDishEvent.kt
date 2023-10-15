package com.zhizhunbao.lib.common.event

import com.zhizhunbao.lib.common.bean.DishBean

class CreateTempDishEvent(var dish: DishBean, var position: Int)