import com.google.gson.annotations.SerializedName

data class DataModel(
  @SerializedName("data" ) var data : LoginUser? = LoginUser()
)

data class LoginUser (
  @SerializedName("username"   ) var username   : String? = null,
  @SerializedName("password"   ) var password   : String? = null,
  @SerializedName("clientType" ) var clientType : String? = null,
  @SerializedName("module"     ) var module     : String? = null

)