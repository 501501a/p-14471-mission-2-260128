import java.util.Scanner;

public class App {
        Scanner sc = new Scanner(System.in);
        int lastId = 0;

        WiseSaying[] wiseSayings = new WiseSaying[10];
        int lastWiseSayingIndex = -1;

    public void run (){

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            }

            if (cmd.equals("등록")) {
                actoinWrite();

            } else if (cmd.equals("목록")) {
                actionList();
            }else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            }else if (cmd.startsWith("수정")) {
                actionModity(cmd);
            }
        }
    }

    private void actionModity(String cmd) {
        String idStr = cmd.split("=")[1];
        int id = Integer.parseInt(idStr);
        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.print("명언(기존) : %s\n".formatted(wiseSaying.content));
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가(기존) : %s\n".formatted(wiseSaying.author));
        System.out.println("작가 : ");
        String author = sc.nextLine();

        mofidy(wiseSaying, content, author);
    }

    private void mofidy(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.author = author;
        wiseSaying.content = content;
    }

    private WiseSaying findById(int modityTarget) {

        for (int i = 0; i <= lastWiseSayingIndex; i++){
            WiseSaying foundedWiseSaying = wiseSayings[i];
            if (modityTarget == foundedWiseSaying.id){
                return wiseSayings[i];
            }
        }
        return null;
    }

    private void actionDelete(String cmd) {

        String idStr = cmd.split("=")[1];
        int id = Integer.parseInt(idStr);

        boolean rst = delete(id);

        if (!rst){
            System.out.println("%d 번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.println("%d 번 명언이 삭제".formatted(id));

    }

    private boolean delete(int deleteTarget) {
        int foundedIndex = -1;

        for (int i = 0; i <= lastWiseSayingIndex; i++){
            WiseSaying foundedWiseSaying = wiseSayings[i];
            if (deleteTarget == foundedWiseSaying.id){
                foundedIndex = i;
            }
        }

        if (foundedIndex == -1) return false;


        for (int i = deleteTarget; i < lastWiseSayingIndex; i++){
            wiseSayings[i] = wiseSayings[i+1];
        }
        --lastWiseSayingIndex;
        return true;
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        WiseSaying[] foundedWiseSayings = findList();

        for (WiseSaying wiseSaying : foundedWiseSayings){
            System.out.println(wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content);
        }
    }

    private  WiseSaying[] findList2(){
        WiseSaying[] foundedWiseSayings = new WiseSaying[lastWiseSayingIndex + 1];
        int foundedWiseSayingIndex = -1;


        for (int i = lastWiseSayingIndex; i >= 0; i--){
            foundedWiseSayings[++foundedWiseSayingIndex] = wiseSayings[i];
        }
        return foundedWiseSayings;
    }

    private WiseSaying[] findList() {

        WiseSaying[] foundedWiseSayings = new WiseSaying[lastWiseSayingIndex +1];
        int foundedWiseSayingIndex = -1;

        for (int i = lastWiseSayingIndex; i >= 0; i--){
            foundedWiseSayings[++foundedWiseSayingIndex] = wiseSayings[i];
        }
        return foundedWiseSayings;
    }

    private void actoinWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying =  write(content, author);
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }

    private WiseSaying write(String content, String author) {

        WiseSaying wiseSaying = new WiseSaying();

        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings[++lastWiseSayingIndex] = wiseSaying;

        return wiseSaying;
    }
}