# ExpenseManagerApp 
  
 An app to Manage and store your Expenses , shows you reports in form of pie charts

## Description

This Android app lets you add your daily expenses and Income like fuel,food,Business etc.<br>
It stores all your transactions and you can get reports in form of Pie chart<br>

## Technologies
 
 - Java <br><br>
 - XML  <br><br>
 - ANDROID <br><br> 
 - MVVM (Model- View View Model) Architecture<br><BR>
 - Room DataBase<br><br>
 
 ## [Getting Started] 
 
 - Clone this repo
 
 ```sh
 git@github.com:FahadBurney/ExpenseManagerApp.git
 ```
- Open the project in <a href="https://developer.android.com/studio"> Android Studio</a>
- Compile and deploy to your Android Emulator for Phone Or Your actual Android Mobile device

## Dependencies
 
    def lifecycle_version = "2.2.0"
    def room_version = "2.2.5"

    implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata:$lifecycle_version"
    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

    implementation "androidx.cardview:cardview:1.0.0"
    implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'

## Screenshots
![MainActivity](https://user-images.githubusercontent.com/64160698/109851795-17acff80-7c7a-11eb-83f7-ad8882eb9c44.jpeg)
![AddExpenses](https://user-images.githubusercontent.com/64160698/109851731-0663f300-7c7a-11eb-83a7-5feba8d7bf83.jpeg)
![AddIncome](https://user-images.githubusercontent.com/64160698/109851745-0a901080-7c7a-11eb-8927-e507ab3cf69f.jpeg)
![AllTransactions](https://user-images.githubusercontent.com/64160698/109851761-0ebc2e00-7c7a-11eb-8837-bf283cc561cc.jpeg)
![CategoryReports](https://user-images.githubusercontent.com/64160698/109851772-111e8800-7c7a-11eb-8ac6-40af9fbb22f2.jpeg)
![EditExpensesActivity](https://user-images.githubusercontent.com/64160698/109851776-12e84b80-7c7a-11eb-90e1-450f82751ad8.jpeg)
![EditIncome](https://user-images.githubusercontent.com/64160698/109851783-154aa580-7c7a-11eb-8466-edf7e0856e2c.jpeg)
![ModeReports](https://user-images.githubusercontent.com/64160698/109851801-1976c300-7c7a-11eb-8dfe-d0693865011b.jpeg)
![TypeReports](https://user-images.githubusercontent.com/64160698/109851807-1aa7f000-7c7a-11eb-9286-a7207e74028a.jpeg)




