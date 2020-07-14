package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="pharsystem.db";
    public static final String TABLE_NAME ="phardene";
    public static final String ID ="ID";
    public static final String USERNAME ="username";
    public static final String PASSWORD ="password";
    public static final String EMAIL ="email";
    public static final String PATIENT_NAME ="patientname";
    public static final String ADDRESS ="address";
    public static final String DEPT ="dept";
    public static final String ILAC_BARCODE ="ilacbarcode";
    public static final String ILAC_ADI ="ilacadi";
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        Toast.makeText(context,"Kurucu Method : Veritabanı Olusturuldu",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE phardene (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT,email TEXT,patientname TEXT,address TEXT,dept INTEGER,ilacbarcode INTEGER,ilacadi TEXT)");
//        Toast.makeText(context," Pharsys tablosu oluşturuldu!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,user);
        contentValues.put(PASSWORD,password);
        contentValues.put(EMAIL,email);
        long res = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERNAME + "=?" + " and " + PASSWORD+ "=?" ;
        String[] selectionArgs = { username, password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    //GETIRDIGIMIZ FONKSYIONLAR BASLIYOR -----------------------------------
    //----------------------------------------------------------------------------------------------


    //İLAC EKLEME TAKKOC
    public void ilacEkle(String ilacadi, int ilac_barcode, Object o) {
        /*SQLiteDatabase: SQLite veritabanı üzerinde işlemler yapabilmek için kullanılır. ekleme, silme ve güncelleme gibi işlemler için kullanılır.
         * getWritableDatabase(): ilk kayıt sırasında veritabanını oluşturur. sonraki durumlarda veritabanını açmak için kullanılır.
         * Temel amacı veritabanı üzerinde yazma ve okuma yapmaktır.*/
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*ContentValues: veritabanına veri eklemek için kullanılır. */
        ContentValues contentValues = new ContentValues();
        /*ContentValues veri ekleme yapısı contentValues.put(sütunadı,veri) şeklindedir.*/
        contentValues.put("ilacadi", ilacadi);
        contentValues.put("ilacbarcode", ilac_barcode);

        /*insert(): veritabanına yeni bir satır yani kayıt eklemek için kullanılır.*/
        sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();
        CreateToastMessage.showMessage(context, "İlac eklendi!");
    }
    public  void ilacEkle2(String ilacadi, int ilacbarcode, String yazar_adi){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        /*ContentValues veri ekleme yapısı contentValues.put(sütunadı,veri) şeklindedir.*/
        contentValues.put("ilacadi", ilacadi);
        contentValues.put(ILAC_BARCODE, ilacbarcode);
        contentValues.put(USERNAME, yazar_adi);

        sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();
        CreateToastMessage.showMessage(context, "İlac eklendi!");

    }
    /*KITAP EKLEME*/
    public void kitapEkle(String kitap_adi, String yazar_adi, String yayinevi, int sayfa) {
        /*SQLiteDatabase: SQLite veritabanı üzerinde işlemler yapabilmek için kullanılır. ekleme, silme ve güncelleme gibi işlemler için kullanılır.
         * getWritableDatabase(): ilk kayıt sırasında veritabanını oluşturur. sonraki durumlarda veritabanını açmak için kullanılır.
         * Temel amacı veritabanı üzerinde yazma ve okuma yapmaktır.*/
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*ContentValues: veritabanına veri eklemek için kullanılır. */
        ContentValues contentValues = new ContentValues();
        /*ContentValues veri ekleme yapısı contentValues.put(sütunadı,veri) şeklindedir.*/
        contentValues.put(PATIENT_NAME, kitap_adi);
        contentValues.put(USERNAME, yazar_adi);
        contentValues.put(ADDRESS, yayinevi);
        contentValues.put(DEPT, sayfa);

        /*insert(): veritabanına yeni bir satır yani kayıt eklemek için kullanılır.*/
        sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();
        CreateToastMessage.showMessage(context, "Patient Record Added");
    }

    /*KITAP LİSTESİ GÖSTERME*/
    public String tumKitaplarYontem1() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*columns: veritabanında okunacak veya seçilecek sütunlar belirlenir.*/
        String[] columns = {ID, PATIENT_NAME, USERNAME, ADDRESS, DEPT};
        /*Cursor: query metodu ile yapılan bir sorgunun sonucu olarak bir cursor döndürülür. Yani bir sorgunun sonucunu temsil etmektedir.
         * query: tablo adı verilen tabloya sorgu göndermek için kullanılır. Sonuç olarak cursor döndürür.*/
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, ADDRESS+" IS NOT NULL ", null, null ,null,null);

        StringBuffer stringBuffer = new StringBuffer();
        /*moveToNext: cursor yani okuma imleci bir sonraki satıra kaydırılır.*/
        while (cursor.moveToNext()) {
            /*cursor.getString: sütunun değerini String olarak ister. Bu metoda parametre olarak bir sayı verilir. Bu sayı sütun numarasıdır.
            cursor.getColumnIndex: sütun ismi verilen sütunun numarasını döndürür. Eğer böyle bir sütun yoksa -1 döndürür.*/
            String kitapId = cursor.getString(cursor.getColumnIndex(ID));
            String kitapAdi = cursor.getString(cursor.getColumnIndex(PATIENT_NAME));
            String yazarAdi = cursor.getString(cursor.getColumnIndex(USERNAME));
            String yayinevi = cursor.getString(cursor.getColumnIndex(ADDRESS));
            /*cursor.getInt: sütunun değerini int olarak ister*/
            int sayfa = cursor.getInt(cursor.getColumnIndex(DEPT));
            stringBuffer.append("Patient ID :  "+kitapId + "\nName :  " + kitapAdi + "\nWho's Patient :  " + yazarAdi + "\nAddress :   " + yayinevi + "\nDept :    " + sayfa + "\n" +"-------"+"\n");
        }
        sqLiteDatabase.close();
        return stringBuffer.toString();
    }

    public String tumKitaplarYontem9(String user_adi) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*columns: veritabanında okunacak veya seçilecek sütunlar belirlenir.*/
        String[] columns = {ID, PATIENT_NAME, USERNAME, ADDRESS, DEPT};
        String[] selectionArgs = {user_adi};

        /*Cursor: query metodu ile yapılan bir sorgunun sonucu olarak bir cursor döndürülür. Yani bir sorgunun sonucunu temsil etmektedir.
         * query: tablo adı verilen tabloya sorgu göndermek için kullanılır. Sonuç olarak cursor döndürür.*/
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns,  USERNAME + " =?"+ "AND " + ADDRESS+" IS NOT NULL ", selectionArgs , null,null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        /*moveToNext: cursor yani okuma imleci bir sonraki satıra kaydırılır.*/
        while (cursor.moveToNext()) {
            /*cursor.getString: sütunun değerini String olarak ister. Bu metoda parametre olarak bir sayı verilir. Bu sayı sütun numarasıdır.
            cursor.getColumnIndex: sütun ismi verilen sütunun numarasını döndürür. Eğer böyle bir sütun yoksa -1 döndürür.*/
            String kitapId = cursor.getString(cursor.getColumnIndex(ID));
            String kitapAdi = cursor.getString(cursor.getColumnIndex(PATIENT_NAME));
            String yazarAdi = cursor.getString(cursor.getColumnIndex(USERNAME));
            String yayinevi = cursor.getString(cursor.getColumnIndex(ADDRESS));
            /*cursor.getInt: sütunun değerini int olarak ister*/
            int sayfa = cursor.getInt(cursor.getColumnIndex(DEPT));
            stringBuffer.append("Patient ID :  "+kitapId + "\nName :  " + kitapAdi + "\nWho's Patient :  " + yazarAdi + "\nAddress :   " + yayinevi + "\nDept :    " + sayfa + "\n" +"-------"+"\n");
        }
        sqLiteDatabase.close();
        return stringBuffer.toString();
    }

    public String tumMedicineYontem9(String med_ad) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*columns: veritabanında okunacak veya seçilecek sütunlar belirlenir.*/
        String[] columns = {ID, PATIENT_NAME, USERNAME, ILAC_ADI, ILAC_BARCODE};
        String[] selectionArgs = {med_ad};

        /*Cursor: query metodu ile yapılan bir sorgunun sonucu olarak bir cursor döndürülür. Yani bir sorgunun sonucunu temsil etmektedir.
         * query: tablo adı verilen tabloya sorgu göndermek için kullanılır. Sonuç olarak cursor döndürür.*/
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns,  USERNAME + " =?"+ "AND " + ILAC_ADI+" IS NOT NULL ", selectionArgs , null,null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        /*moveToNext: cursor yani okuma imleci bir sonraki satıra kaydırılır.*/
        while (cursor.moveToNext()) {
            /*cursor.getString: sütunun değerini String olarak ister. Bu metoda parametre olarak bir sayı verilir. Bu sayı sütun numarasıdır.
            cursor.getColumnIndex: sütun ismi verilen sütunun numarasını döndürür. Eğer böyle bir sütun yoksa -1 döndürür.*/
            String kitapId = cursor.getString(cursor.getColumnIndex(ID));
            String ilacAdi = cursor.getString(cursor.getColumnIndex(ILAC_ADI));
            String yazarAdi = cursor.getString(cursor.getColumnIndex(USERNAME));
            String barcode = cursor.getString(cursor.getColumnIndex(ILAC_BARCODE));
            /*cursor.getInt: sütunun değerini int olarak ister*/

            stringBuffer.append("Patient ID :  "+kitapId + "\nName :  " + ilacAdi + "\nWho's Patient :  " + yazarAdi + "\nAddress :   " + barcode + "\n" +"-------"+"\n");
        }
        sqLiteDatabase.close();
        return stringBuffer.toString();
    }

    public String tumKitaplarYontem2() {
        String kitapAdi = null, yazarAdi = null, yayinevi = null, kitapId = null;
        int sayfa = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        /*rawQuery: verilen sql cümlesini çalıştırır ve sonuç olarak bir cursor döndürür.*/
        Cursor cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {
            /*cursor.getColumnCount: tablodaki toplam sütun sayısını döndürür.*/
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                /*cursor.getColumnName: index değeri verilen sütunun ismini döndürür.*/
                String currentColumnName = cursor.getColumnName(i);
                switch (currentColumnName) {
                    case ID:
                        kitapId = cursor.getString(i);
                        break;
                    case PATIENT_NAME:
                        kitapAdi = cursor.getString(i);
                        break;
                    case USERNAME:
                        yazarAdi = cursor.getString(i);
                        break;
                    case ADDRESS:
                        yayinevi = cursor.getString(i);
                        break;
                    case DEPT:
                        sayfa = cursor.getInt(i);
                        break;
                }
            }
            stringBuffer.append(kitapId + " " + kitapAdi + " " + yazarAdi + " " + yayinevi + " " + sayfa + "\n");
        }
        cursor.close();
        sqLiteDatabase.close();
        return stringBuffer.toString();
    }
    /*MEDİCİNE GÖSTERME ADA GORE*/
    public String[] medicineGosterAd(String medicineAad) {
        String kitapAdi = null, yazarAdi = null, yayinevi = null;
        int sayfa = 0;
        String ilacId=null;
        int ilacBarcode=0;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*columns: tabloda seçilecek sütunlar burada belirlenir.*/
        String[] columns = {ID, ILAC_ADI, USERNAME,  ILAC_BARCODE};
        /*selectionArgs: tabloda özellikle seçilecek veriler burada belirlenir. id bilgisi verilen kitap seçilecektir.*/
        String[] selectionArgs = {String.valueOf(medicineAad)};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, ILAC_ADI + " =?", selectionArgs, null, null, null);
        String[] ilacBilgisi = new String[0];
        /*getCount: cursor'de bulunan toplam satır sayısını döndürür.*/
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ilacId = cursor.getString(cursor.getColumnIndex(ID));
                yazarAdi = cursor.getString(cursor.getColumnIndex(USERNAME));
                ilacBarcode = cursor.getInt(cursor.getColumnIndex(ILAC_BARCODE));
                ilacBilgisi = new String[]{ilacId, yazarAdi, String.valueOf(ilacBarcode)};
            }
        } else {
            ilacBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }

        cursor.close();
        sqLiteDatabase.close();
        return ilacBilgisi;
    }
    /*KITAP GÖSTERME*/
    public String[] kitapGostermeYontem1(int kitap_id) {
        String kitapAdi = null, yazarAdi = null, yayinevi = null;
        int sayfa = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*columns: tabloda seçilecek sütunlar burada belirlenir.*/
        String[] columns = {ID, PATIENT_NAME, USERNAME, ADDRESS, DEPT};
        /*selectionArgs: tabloda özellikle seçilecek veriler burada belirlenir. id bilgisi verilen kitap seçilecektir.*/
        String[] selectionArgs = {String.valueOf(kitap_id)};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, ID + " =?", selectionArgs, null, null, null);
        String[] kitapBilgisi = new String[0];
        /*getCount: cursor'de bulunan toplam satır sayısını döndürür.*/
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                kitapAdi = cursor.getString(cursor.getColumnIndex(PATIENT_NAME));
                yazarAdi = cursor.getString(cursor.getColumnIndex(USERNAME));
                yayinevi = cursor.getString(cursor.getColumnIndex(ADDRESS));
                sayfa = cursor.getInt(cursor.getColumnIndex(DEPT));
                kitapBilgisi = new String[]{kitapAdi, yazarAdi, yayinevi, String.valueOf(sayfa)};
            }
        } else {
            kitapBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }

        cursor.close();
        sqLiteDatabase.close();
        return kitapBilgisi;
    }

    /*TAKKOC MEDİCİNE GÖSTERME*/
    public String[] medicineGostermeYontem1(int ilac_id) {
        String medicineAdi = null;
        String yazarAdi = null;
        int medicine_barcode;
        int sayfa = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*columns: tabloda seçilecek sütunlar burada belirlenir.*/
        String[] columns = {ID,ILAC_ADI,USERNAME, ILAC_BARCODE};
        /*selectionArgs: tabloda özellikle seçilecek veriler burada belirlenir. id bilgisi verilen kitap seçilecektir.*/
        String[] selectionArgs = {String.valueOf(ilac_id)};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, ID + " =?", selectionArgs, null, null, null);
        String[] ilacBilgisi = new String[0];
        /*getCount: cursor'de bulunan toplam satır sayısını döndürür.*/
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                medicineAdi = cursor.getString(cursor.getColumnIndex(ILAC_ADI));
                yazarAdi = cursor.getString(cursor.getColumnIndex(USERNAME));
                medicine_barcode = cursor.getInt(cursor.getColumnIndex(ILAC_BARCODE));

            }
        } else {
            ilacBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }

        cursor.close();
        sqLiteDatabase.close();
        return ilacBilgisi;
    }


    /*TUM MEDİCİNE LİSTESİ GÖSTERME*/
    public String tumMedicineGostermeYontem1() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*columns: veritabanında okunacak veya seçilecek sütunlar belirlenir.*/
        String[] columns = {ID, ILAC_ADI,USERNAME, ILAC_BARCODE};
        /*Cursor: query metodu ile yapılan bir sorgunun sonucu olarak bir cursor döndürülür. Yani bir sorgunun sonucunu temsil etmektedir.
         * query: tablo adı verilen tabloya sorgu göndermek için kullanılır. Sonuç olarak cursor döndürür.*/
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, ILAC_ADI+" IS NOT NULL ", null, null, null, null);
        // String secilecek = String.valueOf(!KITAP_TABLO_YAPISI.ILAC_ADI.equals("null"));


        //Cursor cursor2= sqLiteDatabase.query(KITAP_TABLO_YAPISI.TABLO_ADI, columns,KITAP_TABLO_YAPISI.KITAP_ID + " =? AND" +KITAP_TABLO_YAPISI.ILAC_ADI+"IS NOT NULL AND"+KITAP_TABLO_YAPISI.ILAC_ADI+" !=''" ,null,null,null,null,null);
        //Cursor mCursor = SQLiteDatabase.query(KITAP_TABLO_YAPISI.TABLO_ADI,columns ,KITAP_TABLO_YAPISI.ILAC_ID + "=? AND NOT "+KITAP_TABLO_YAPISI.ILAC_ADI+" = ''" , null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        /*moveToNext: cursor yani okuma imleci bir sonraki satıra kaydırılır.*/
        while (cursor.moveToNext()) {
            /*cursor.getString: sütunun değerini String olarak ister. Bu metoda parametre olarak bir sayı verilir. Bu sayı sütun numarasıdır.
            cursor.getColumnIndex: sütun ismi verilen sütunun numarasını döndürür. Eğer böyle bir sütun yoksa -1 döndürür.*/
            int  ilacId = cursor.getInt(cursor.getColumnIndex(ID));
            String ilacAdi = cursor.getString(cursor.getColumnIndex(ILAC_ADI));
            String yazarAdi = cursor.getString(cursor.getColumnIndex(USERNAME));
            /*cursor.getInt: sütunun değerini int olarak ister*/
            int ilacBarcode = cursor.getInt(cursor.getColumnIndex(ILAC_BARCODE));

            stringBuffer.append("Medicine ID :  " + ilacId + "\nName :  " + ilacAdi + "\nWho's Patient :  " + yazarAdi + "\nBarcone No  :    " + ilacBarcode + "\n" + "-------" + "\n");


        }
        sqLiteDatabase.close();
        return stringBuffer.toString();
    }


    /*KITAP GÖSTERME22222222222222222*/
    public String[] kitapGostermeYontem0(String user_adi) {
        String kitapAdi = null, yazarAdi = null, yayinevi = null;
        int sayfa = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*columns: tabloda seçilecek sütunlar burada belirlenir.*/
        String[] columns = {ID, PATIENT_NAME, USERNAME, ADDRESS, DEPT};
        /*selectionArgs: tabloda özellikle seçilecek veriler burada belirlenir. id bilgisi verilen kitap seçilecektir.*/
        String[] secilen = {user_adi};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columns, USERNAME + " =?", secilen, null, null, null);
        String[] userbilgisi = new String[0];
        /*getCount: cursor'de bulunan toplam satır sayısını döndürür.*/
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                kitapAdi = cursor.getString(cursor.getColumnIndex(PATIENT_NAME));
                yazarAdi = cursor.getString(cursor.getColumnIndex(USERNAME));
                yayinevi = cursor.getString(cursor.getColumnIndex(ADDRESS));
                sayfa = cursor.getInt(cursor.getColumnIndex(DEPT));
                userbilgisi = new String[]{kitapAdi, yazarAdi, yayinevi, String.valueOf(sayfa)};
            }
        } else {
            userbilgisi = new String[]{String.valueOf(cursor.getCount())};
        }

        cursor.close();
        sqLiteDatabase.close();
        return userbilgisi;
    }


/*
    public String[] kitapGostermeYontem2(String user_adi) {
        String kitapAdi = null, yazarAdi = null, yayinevi = null;
        int sayfa = 0;
        String query11 = "SELECT * FROM " + KITAP_TABLO_YAPISI.TABLO_ADI + " WHERE YAZAR_ADI=" + user_adi;
        String  query = "SELECT * FROM KITAP_TABLO_YAPISI WHERE YAZAR_ADI like user_adi";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String[] userBilgisi = new String[0];
        cursor.moveToNext();
        if (cursor.getCount() > 0) {
            kitapAdi = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.KITAP_ADI));
            String userAdi = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.YAZAR_ADI));
            yayinevi = cursor.getString(cursor.getColumnIndex(KITAP_TABLO_YAPISI.YAYINEVI));
            sayfa = cursor.getInt(cursor.getColumnIndex(KITAP_TABLO_YAPISI.SAYFA));
            userBilgisi = new String[]{kitapAdi, yazarAdi, yayinevi, String.valueOf(sayfa)};
        }else {
            userBilgisi = new String[]{String.valueOf(cursor.getCount())};
        }

        cursor.close();
        sqLiteDatabase.close();
        return userBilgisi;

    }
*/

    /*KITAP SİLME*/
    public void kitapSil(int kitap_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] silinecekVeri = {String.valueOf(kitap_id)};
        /*delete: tablodan  satır silmek için kullanılır*/
        sqLiteDatabase.delete(TABLE_NAME, ID + " =?", silinecekVeri);
        sqLiteDatabase.close();
        CreateToastMessage.showMessage(context, "Patient Have Been Deleted!");
    }

    public  void deleteAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL("delete from "+ TABLE_NAME);
        sqLiteDatabase.close();

    }

    /*KITAP GÜNCELLEME*/
    public void kitapGuncelleme(int kitap_id, String yeniYayinevi, int yeniSayfa) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ADDRESS, yeniYayinevi);
        contentValues.put(DEPT, yeniSayfa);
        /*update: tabloda veri güncellemek için */
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ? ", new String[]{String.valueOf(kitap_id)});
        sqLiteDatabase.close();
        CreateToastMessage.showMessage(context, "Patient güncellendi!");
    }





}