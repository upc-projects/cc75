package pe.edu.upc.catchup


import com.androidnetworking.AndroidNetworking
import com.orm.SugarApp

class CathUpApp : SugarApp() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(getApplicationContext())
    }
}