import com.google.gson.annotations.SerializedName

data class DataModel(
  @SerializedName("authenticated" ) var authenticated : Boolean,
  @SerializedName("clientType" ) var clientType : String,
  @SerializedName("client" ) var client : Client
)

data class Client(
  @SerializedName("authStatus" ) var authStatus : Int
)