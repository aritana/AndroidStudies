package tests.section3.timer
import android.os.CountDownTimer

Timer(){

    object : CountDownTimer(30000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
        }

        override fun onFinish() {
            mTextField.setText("done!")
        }
    }.start()

}

