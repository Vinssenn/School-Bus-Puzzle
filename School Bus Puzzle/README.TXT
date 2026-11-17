------------------------------------------------------------------------
SCHOOL BUS PUZZLE
------------------------------------------------------------------------

PROJECT TITLE: School Bus Puzzle
PURPOSE OF PROJECT: Game puzzle blok. Isi semua kotak kosong di papan tulis
    bus sekolah dengan piece yang tersedia di rak bawah. 10 level, 3 bintang
    per level (berdasarkan waktu).
VERSION or DATE: November 2026 (versi lengkap)
HOW TO START THIS PROJECT: Buka project.greenfoot di Greenfoot, klik
    "Compile All" (kalau diminta), lalu tekan Run. Game mulai dari Lobby.
AUTHORS: (isi nama kamu di sini)

USER INSTRUCTIONS:
  - Lobby: PLAY membuka peta level, SETTING mengatur volume (musik + efek).
  - Peta: klik node level. Level 1 selalu terbuka; level berikutnya terbuka
    setelah level sebelumnya selesai. Bintang yang didapat tampil di node.
  - Di dalam level:
      * Seret piece dari rak ke papan; piece otomatis pas ke kotak terdekat.
        Kotak hijau = bisa dipasang, merah = tidak bisa.
      * Klik piece (atau klik kanan) untuk memutar 90 derajat. Saat
        menyeret, tekan R atau Spasi untuk memutar.
      * Piece yang sudah terpasang boleh diangkat lagi dan dipindah; lepas
        di area rak untuk mengembalikannya.
      * Tombol bulat kiri-atas: Pause (keluar ke peta, ada konfirmasi) dan
        Reset (ulang level).
      * Papan penuh = level selesai. 3 bintang kalau waktu <= TARGET,
        2 bintang kalau <= 2x TARGET, selain itu 1 bintang.

CATATAN UNTUK PENGEMBANG:
  - Data level ada di Levels.java (grid: '.' kosong, '#' blok abu-abu,
    ' ' tidak dipakai; piece: O kotak, I balok, T, S, U = piece biru "L").
    Setiap level sudah dicek bisa diselesaikan.
  - Logika drag/drop semuanya di game1.java (berbasis grid, bukan
    isTouching, jadi bentuk piece yang saling mengunci bisa dipasang pas).
  - Progress bintang disimpan selama game berjalan (kelas Progress);
    reset Greenfoot = progress kembali ke awal.
