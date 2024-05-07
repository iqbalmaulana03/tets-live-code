"Project login-system adalah sebuah aplikasi yang dikembangkan menggunakan Java untuk bagian backend dan React JS untuk bagian frontend. Aplikasi ini dirancang untuk memberikan pengguna kemampuan untuk login ke dalam sistem dengan aman dan efisien.

Bagian backend dari proyek ini dikembangkan menggunakan Java, sebuah bahasa pemrograman yang kuat dan populer untuk pengembangan aplikasi berbasis server. Backend ini bertanggung jawab untuk mengelola autentikasi pengguna, menyimpan data pengguna, dan memberikan layanan API untuk interaksi dengan frontend.

Di sisi lain, frontend dari proyek ini menggunakan React JS, sebuah framework JavaScript yang cepat dan efisien untuk membangun antarmuka pengguna yang interaktif. Frontend ini menyediakan antarmuka yang ramah pengguna untuk pengguna melakukan login, menampilkan informasi pengguna, dan mengelola sesi login mereka.

Dengan menggunakan Java untuk backend dan React JS untuk frontend, proyek login-system ini memberikan pengalaman pengguna yang mulus dan responsif, sambil menjaga keamanan dan kinerja sistem secara menyeluruh."

#Installation
Untuk run project java nya bisa langsung dengan fitur run di code editor intellij idea atau bisa menggunakan :
  - DATABASE_USERNAME=root DATABASE_PASSWORD=password DB_HOST=db DB_PORT=3306 JWT_SECRET=rahasia JWT_EXPIRATION_IN_SECOND=86400 docker compose up (Untuk Linux)
  - set DATABASE_USERNAME=root
    set DATABASE_PASSWORD=password
    set DB_HOST=db
    set DB_PORT=3306
    set JWT_SECRET=rahasia
    set JWT_EXPIRATION_IN_SECOND=86400
    ocker compose up (Untuk windows)
Untuk Frontend nya anda bisa dengan melakukan npm start

untuk documentationnya anda bisa menggunakan http://localhost:8080/swagger-ui/index.html#/
