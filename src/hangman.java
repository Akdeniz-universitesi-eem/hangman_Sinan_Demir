import java.util.Scanner;

public class hangman {

    public static void main(String[] args) {
       game();
    }
    public  static void game()
    {
        String[] cities = {"İSTANBUL", "ANKARA", "İZMİR", "BURSA", "ANTALYA",
                "ADANA", "KONYA", "MARDİN", "ESKİŞEHİR", "DİYARBAKIR"};
        // rastgele şehiri seçer
        int num = (int) (Math.random() * 9);
        String city = cities[num];
        int cL = city.length();
        System.out.printf("****** %d Harfli bir şehir ******\n", cL);

        char[] word = new char[cL];

        // Kelime sayısı kadar alt tire olan dizi atar
        for (int k = 0; k < cL; k++)
            word[k] = '_';

        // Kullanıcıya 5 Hata hakkı verir
        int vote=5;

        // Başlangıc zamanını tutar
        long startTime = System.currentTimeMillis();
        while (vote!=0) {

            // Seçilen kelimenin olacağı diziyi yazar
            for (int i = 0; i < cL; i++)
                System.out.print(word[i]);

            // KULLANICIDAN GİRDİ ALIR
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nHarfi Veya Kelimeyi Tahmin Ediniz : ");
            String guess = scanner.nextLine();

            // Geçen süreyi hesaplar
            long passTime=(System.currentTimeMillis()-startTime);
            float passTimeFloat= (float) passTime /1000;

            // Kullanıcıyı Char girmeye zorlar ve onu guess1 charına atar
            if (Character.isLetter(guess.toCharArray()[0])) {
            char guess1=' '; // guess1 Charını Seçilen şehrin her bir harfi ile karşılaştırmak için var

            // eğer kullanıcı 1 tane String değer girmişsse yani Harf tahmin ediyorsa onu guess1'e atar.
            if (guess.length()==1) {
                guess1=guess.charAt(0);
            }

            /* Eğer kullanıcı 1 den fazla Harf girmişse  tahmin ediyordur diyip Tahminini doğrulama yapar.
            Doğru ise oyunu bitirir ve ekrana yazar */
            else if (guess.toUpperCase().equals(city)) {
                System.out.print("*** TEBRİKLER BİLDİNİZ ***" );
                System.out.printf("\nGeçen süre : %.2f Saniye\n",passTimeFloat);
                System.out.println("Skorunuz : "+Score(passTimeFloat,vote));
                break; }

            /* kullanıcı harf girmişsse Şehrin her bir karakterini tarar ve eşleme yaparsa bunu daha önce oluşturulan
                word dizisinin ilgili indexine atar */
                boolean corret=false;
            for (int i = 0; i < cL; i++) {
                char a = city.toCharArray()[i];
                if (Character.isLowerCase(guess1))
                    guess1 = Character.toUpperCase(guess1);
                if (guess1 == 'I') guess1 = 'İ';

                // Kullanıcının girdiği harf şehirdeki bir karakter ile eşleşirse word dizisinin o anki indexe atar
                if (guess1 == a) {
                    corret=true;
                    word[i] = guess1;
                }
            }
                // estetik açıdan yeni satıra geçiyoruz
                System.out.println();
            // Kullanıcının bilmediği her harf için Adam asılır
            if (!corret) {
                vote --;
                System.out.println("\n**** YANLIŞ **** ");
                hangmanImage(vote);
            }
            if (corret) System.out.println("**** DOĞRU ****");
            if (vote==0) {
                System.out.println("\n *** KAYBETTİNİZ ***");
                System.out.println("Doğru Cevap : "+city);
                System.out.println("Skorunuz : "+Score(passTimeFloat,vote));
            }
         }
            // olaki kullanıcı Char dışında bir karakter girerse Onu nazikçe uyarıyoruz :)
            else System.out.println("\n**** Harf Al Veya Şehri Tahmin ET ! ****\n");
       }
    }

    public static void hangmanImage(int mistake){
        switch(mistake) // her yanlış bilinen harf için adam fiğüri çizdirir.
        {  case 4 : System.out.print("\n\t\t|\n"); break;
            case 3 : System.out.print("\n\t\t|\n\t\tO\n"); break;
            case 2 : System.out.print("\n\t\t|\n\t\tO\n\t\t|\n"); break;
            case 1 : System.out.print("\n\t\t|\n\t\tO\n\t  --|-- \n"); break;
            case 0 : System.out.print("\n\t\t|\n\t\tØ\n\t  --|--\n\t   / \\ "); break;
        }
    }


    // Zamana ve yapılan yanlışa göre Puanlama sistemi
    public static int Score(float time,int mistake){

        // ilk önce yapılan yanlışa göre uygun case seçilir.Daha sonra Süreye göre Çeşitli puanlama yapılır.
        switch (mistake) {
            case 5 :
            { if (0<time && time<20) return 100;
            else if (20<time && time<45)  return 90;
            else return 85;
            }
            case 4 :
            {   if (0<time && time<50) return 80;
                else return 70;
            }
            case 3 :
            {
                if (0<time && time<60) return 60;
                else return 50;
            }
            case 2 :
            {
                if (0<time && time<75) return 45;
                else return 40;
            }
            case 1 :
            {
                if (0<time && time<90) return 35;
                else return 30;
            }
            default: return 0;
    }
}
}
