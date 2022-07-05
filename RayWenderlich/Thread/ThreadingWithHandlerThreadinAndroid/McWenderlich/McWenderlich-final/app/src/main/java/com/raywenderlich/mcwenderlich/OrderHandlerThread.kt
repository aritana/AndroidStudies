/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.raywenderlich.mcwenderlich

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import java.util.*

class OrderHandlerThread(private var uiHandler: MainActivity.UiHandler) :
    HandlerThread("OrderHandlerThread") {

  private var handler: Handler? = null
  private var random: Random = Random()

  override fun onLooperPrepared() {
    super.onLooperPrepared()
    handler = getHandler(looper)
  }

  fun sendOrder(foodOrder: FoodOrder) {
    val message = Message()
    message.obj = foodOrder
    handler?.sendMessage(message)
  }

  private fun getHandler(looper: Looper): Handler {
    return object : Handler(looper) {

      override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        val foodOrder = msg?.obj as FoodOrder
        foodOrder.foodPrice = convertCurrency(foodOrder.foodPrice)
        foodOrder.sideOrder = attachSideOrder()
        val processedMessage = Message()
        processedMessage.obj = foodOrder
        uiHandler.sendMessage(processedMessage)
      }
    }
  }

  /**
   * Function to convert the food price from USD to INR.
   * 1 USD has been considered as equal to 68.45 Indian Rupees.
   * @foodPriceInDollars price of the food in USD.
   */
  private fun convertCurrency(foodPriceInDollars: Float): Float {
    return foodPriceInDollars * 68.45f
  }

  /**
   * This function attaches random side order to the incoming food Orders.
   */
  private fun attachSideOrder(): String {
    val randomOrder = random.nextInt(3)
    return when (randomOrder) {
      0 -> "Chips"
      1 -> "Salad"
      else -> "Nachos"
    }
  }
}
