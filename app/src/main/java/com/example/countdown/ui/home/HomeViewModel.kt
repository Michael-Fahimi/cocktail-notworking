import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countdown.ui.home.Cocktail
import com.example.countdown.ui.home.CocktailResponse
import com.example.countdown.ui.home.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Cocktail>>()
    val items: LiveData<List<Cocktail>> get() = _items

    fun fetchCocktailsByLetter(letter: String) {
        RetrofitClient.instance.getCocktailsByFirstLetter(letter).enqueue(object : Callback<CocktailResponse> {
            override fun onResponse(call: Call<CocktailResponse>, response: Response<CocktailResponse>) {
                if (response.isSuccessful) {
                    _items.value = response.body()?.drinks ?: emptyList()
                } else {
                    _items.value = emptyList()
                }
            }

            override fun onFailure(call: Call<CocktailResponse>, t: Throwable) {
                _items.value = emptyList()
            }
        })
    }
}