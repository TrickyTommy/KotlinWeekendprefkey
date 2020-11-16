# KotlinWeekendprefkey
# S.O.L.I.D
- <b>S</b>ingle Responsibility Priciple
merupakan sebuah prinsip yang sering dipakai di dalam pembuatan aplikasi, dimana prinsip ini digunakan untuk mengatur tanggung jawab suatu class berdasarkan fungsionalitas.
```KOTLIN 
data class Robot(val name: String, val type: String)

class RobotPrinter {
   fun greet(robot: Robot) {
       val name = robot.name
       val type = robot.type
       println("Hello my name is $name and I am a $type robot")
   }
}
```
- <b>O</b>pen / Closed Priciple
Open / closed principle merupakan prinsip yang setiap class dan member di dalamnya harus terbuka untuk diwariskan, namun tertutup untuk dimodifikasi oleh kelas turunannya.
```KOTLIN
interface Network {
   fun broadcast(message: String)
}

class HttpNetwork: Network {
   private val uri = URI("https://robot-hq.com")

   override fun broadcast(message: String) {
       val httpClient = HttpClient.newHttpClient()
       val request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(message)).build()
       httpClient.send(request, HttpResponse.BodyHandlers.discarding())
   }
}
```
- <b>L</b>iskov Substitution Principle
Liskov substitution principle merupakan prinsip yang mengatur subclass harus meng-override method dari superclass tanpa harus merusak fungsionalitas dari superclass.
```KOTLIN
abstract class Robot {
   abstract fun goToLocation(lat: Double, long: Double)
}
abstract class LightweightRobot: Robot() {
    abstract fun jump()
}
```
- <b>I</b>nterface Segregation Principle
Interface segregation principle adalah prinsip yang mengatur class untuk tidak mengimplementasikan function yang tidak dipakai. Interface dengan function yang memiliki spesifik function lebih baik daripada interface yang memiliki banyak function yang general.
```KOTLIN
interface WavingRobot {
   fun wave()
}

interface MobileRobot {
    fun goToLocation(lat: Double, long: Double)
}
```
- <b>D</b>ependency Inversion Principle
Dependency inversion principle adalah prinsip yang mengatur bahwa high level class (class yang memiliki kumpulan fungsionalitas) tidak boleh bergantung kepada low level class (class yang hanya berurusan dengan fungsionalitas yang detail).
```KOTLIN
class JobNotifier(private val notifier: Notifier) {
   fun notifyJob() {
       if (notifier is Email) notifier.sendAlert("You are alerted from EMAIL")
       else if (notifier is WhatsApp) notifier.sendAlert("You are alerted from WhatsApp")
   }
}

interface Notifier {
   fun sendAlert(alert: String)
}

class Email : Notifier {
   override fun sendAlert(alert: String) = print(alert)
}

class WhatsApp : Notifier {
   override fun sendAlert(alert: String) = print(alert)
}
```
# Android Introduction
- Android Manifest
- Activity
- Handling and Actions
- Menu
- Dialog
- App Icon
- RecyclerView 
- Adapter
- ViewHolder
- Shared Preferences
- Read dan 
- Save
- Intent
- Filters
- Parcels
- Fragment
- Frame layout
- Fragment Transaction
### Android Manifest
```KOTLIN
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
   package="id.refactory.counterapp">

   <application
       android:allowBackup="true"
       android:icon="@mipmap/ic_launcher"
       android:label="@string/app_name"
       android:roundIcon="@mipmap/ic_launcher_round"
       android:supportsRtl="true"
       android:theme="@style/AppTheme">
       <activity android:name=".MainActivity">
           <intent-filter>
               <action android:name="android.intent.action.MAIN" />
               <category android:name="android.intent.category.LAUNCHER" />
           </intent-filter>
       </activity>
   </application>

</manifest>
```
### Activity
suatu hal yang berfokus pada satu hal yang dilakukan oleh user. Sebagian besar activity berinteraksi dengan user, seperti click, scroll, drag dsb.
### Handling 
Android dapat menerima beberapa macam input dari user, seperti: onClick, OnTextChanged, onItemSelected, onCheckedChange dsb.
### Menu
Menu adalah komponen antarmuka pengguna yang banyak dijumpai di berbagai jenis aplikasi, menu memberikan kemudahan kepada user dalam memilih tindakan di dalam aplikasi.
Ada beberapa macam menu yang bisa digunakan di dalam Android:
- Options menu dan app bar.
- Context menu dan contextual action mode.
- Popup menu
### Dialogs
Dialog adalah tampilan kecil yang digunakan untuk meminta user untuk memilih tindakan atau memasukkan informasi tambahan.
- Alert Dialog
- DatePickerDialog 
### App Icon
App Icon pada Android mendukung untuk berbagai macam tema yang dimiliki oleh user, seperti icon circle, square, legacy dsb.

### RecyclerView
RecyclerView adalah sebuah scrollable view yang menampilkan data berupa iterable pada Android, RecyclerView merupakan versi yang lebih canggih dari ListView dimana RecyclerView akan melakukan binding pada data yang terlihat saja.
```KOTLIN
implementation 'androidx.recyclerview:recyclerview:1.1.0'
```
Pemanggilan RecyclerView di dalam XML Android:
```KOTLIN
<androidx.recyclerview.widget.RecyclerView xmlns:android="http://schemas.android.com/apk/res/android"
   xmlns:app="http://schemas.android.com/apk/res-auto"
   android:id="@+id/rvList"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager" />
  ```
### Adapter
Digunakan untuk memasukkan data iterable ke dalam RecyclerView, mengganti data iterable yang baru, mengganti tampilan saat konten tidak terlihat dan hanya menampilkan data pada konten yang terlihat saja.
```KOTLIN
class NameAdapter : RecyclerView.Adapter<NameAdapter.ViewHolder>() {

   class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       TODO("Not yet implemented")
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) { TODO("Not yet implemented") }

   override fun getItemCount(): Int { TODO("Not yet implemented") }
}
```
### View Holder
sebuah class yang digunakan untuk melakukan proses binding data pada setiap item pada RecyclerView di android.
```KOTLIN
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
   fun bindData(s: String) {
       val tvName = itemView.findViewById<TextView>(android.R.id.text1)
       tvName.text = s
   }
}
```
### SharedPreferences
Penyimpanan lokal untuk small data collection yang mempunyai key dan value pada Android.
private const val SHARED_TODO = "id.refactory.todolistapp"
private lateinit var shared: SharedPreferences
```KOTLIN
fun getPref(context: Context): SharedPreferences {
   if (!this::shared.isInitialized) shared =
       context.getSharedPreferences(SHARED_TODO, Context.MODE_PRIVATE)
   return shared
```
### Save Data
SharedPreferences menyimpan data yang bersifat simple seperti Int, Boolean, String, Float, dsb. Dalam menyimpan dan mengubah data di SharedPreferences perlu memanggil API SharedPreferences.Editor kemudian apply penambahan dan perubahannya.
```KOTLIN
inline fun <reified T> SharedPreferences.save(key: String, value: T) {
   edit {
       when (value) {
           is Boolean -> putBoolean(key, value)
           is Int -> putInt(key, value)
           is String -> putString(key, value)
           is Long -> putLong(key, value)
           is Float -> putFloat(key, value)
           else -> println("Don't save data with this data type, instead of save on local database")
       }
   }
}
val pref = SharedUtil.getPref(this)
pref.save("Boolean", true)
pref.save("Int", 1000)
pref.save("String", "Hello world")
pref.save("Float", 10.0f)
```
### Read Data
Mengambil data pada SharedPreferences harus eksplisit tipe datanya, semisal mengambil data String maka harus menggunakan method getString(key). haredPreferences juga menyediakan input untuk default valuenya, ketika data yang disimpan adalah null atau tidak ada.
```KOTLIN
val pref = SharedUtil.getPref(this)
pref.save("Boolean", true)
pref.save("Int", 1000)
pref.save("String", "Hello world")
pref.save("Float", 10.0f)

pref.getString("String", "")
pref.getInt("Int", 0)
pref.getBoolean("Boolean", false)
pref.getFloat("Float", 0.0f)
```
### Intent
Intent adalah sebuah kelas dalam programming Android yang berfungsi untuk perpindahan halaman.
Intent juga merupakan suatu objek yang terdapat dalam suatu activity dimana objek tersebut dapat komunikasi dengan activity yang lain, baik activity pada fungsi internal android misal seperti memanggil activity dalam satu package atau beda package yang masih berada dalam satu project. 
```KOTLIN
val intent = Intent(this, SecondActivity::class.java).putExtra(DATA, 100)
startActivity(intent)
```
### Filters
Intent filter merupakan ekspresi dari sebuah komponen di dalam file manifest pada aplikasi Android. Intent filter digunakan untuk menentukan jenis intent yang diterima oleh komponen.
```KOTLIN
<activity android:name=".MainActivity">
   <intent-filter>
       <action android:name="android.intent.action.MAIN" />
       <category android:name="android.intent.category.LAUNCHER" />
   </intent-filter>
</activity>
```
### Parcels
Sebuah object bundle yang digunakan untuk memberikan data berupa object kepada komponen Android lainnya berdasarkan key.
```KOTLIN
@Parcelize
data class User(val name: String, val age: Int) : Parcelable
override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)
   setContentView(R.layout.activity_main)
   btnNext.setOnClickListener {
       val user = User("Angga Saputra", 20)
       val intent = Intent(this, SecondActivity::class.java).apply { putExtra(DATA, user) }
       startActivity(intent)
   }
}
```
### Fragment
Fragment mewakili porsi dari user interface pada sebuah FragmentActivity, Fragment mensegmentasi aplikasi menjadi beberapa layar independen yang dikumpulkan dalam suatu Activity.
```KOTLIN
class BlankFragment : Fragment() {
   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View? {
       val view = inflater.inflate(R.layout.fragment_blank, container, false)
       view.setOnClickListener { Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show() }
       return view
   }
}
```
### Frame Layout
FrameLayout adalah view berupa container yang akan menampung fragment pada Activity sedang berjalan.
```KOTLIN
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
   xmlns:app="http://schemas.android.com/apk/res-auto"
   xmlns:tools="http://schemas.android.com/tools"
   android:id="@+id/flMain"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   tools:context=".MainActivity"/>
```
### Fragment Transition
FragmentManager class dan FragmentTransaction class digunakan untuk menambahkan, menghapus dan mengganti fragment di FrameLayout di dalam Activity pada saat runtime.
```KOTLIN
supportFragmentManager.beginTransaction().add(R.id.flMain, BlankFragment()).commit()
supportFragmentManager.beginTransaction().replace(R.id.flMain, BlankFragment()).commit()
supportFragmentManager.beginTransaction().remove(BlankFragment())
```










