Features:
- A list of repositories page where list of repositories showed.
- List fetched from github repository search api using "Android" as query keyword.
- List can be sorted by either last update date time or star count.
- Selected sorting option persists in further app sessions.
- A repo details page, to which navigated by clicking on an item from the list.
- Details page shows repo owner's name, photo, repository's description, last update date
time in month-day-year hour:seconds format, each field in 2 digit numbers.
- The repository which loaded once, is saved for offline browsing.

Technical Stack:
- Language: Kotlin
- Design patterns: Repository, Singleton
- Architecturatl pattern: MVVM
- Api client: Retrofit
- Offline caching with Room
- Async callings - Kotlin coroutine
- Jetpack navigation components
- View Binding & Data Binding
- Simple Unit test
