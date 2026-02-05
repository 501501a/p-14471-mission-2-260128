import java.util.Scanner;

public class App {
        Scanner sc = new Scanner(System.in);
        int lastId = 0;
        int lastwiseSayingIndex = -1;
        WiseSaying[] wiseSayings = new WiseSaying[10];


    public void run(){
        System.out.println("== 명언 앱 ==");

        while (true){
            System.out.println("명령) ");
            String cmd = sc.nextLine();
            if (cmd.equals("종료")){
                break;
            }
            if (cmd.equals("등록")){
               actionWrite();
            }
            else if (cmd.equals("목록")){
                actionList();
            } else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정")){
                actrionModify(cmd);
            }
        }

    }

    private void actrionModify(String cmd) {
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
        System.out.print("작가(기존) : %s\n".formatted(wiseSaying.author));
        System.out.print("작가 : ");
        String author = sc.nextLine();

        modify(wiseSaying, content, author);
    }

    private void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.author = author;
        wiseSaying.content = content;
    }

    private WiseSaying findById(int modifyTarget) {
        for (int i = 0; i <= lastwiseSayingIndex; i++) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            if (modifyTarget == foundedWiseSaying.id) {
                return wiseSayings[i];
            }
        }return null;
    }

    private void actionDelete(String cmd) {

        String idStr = cmd.split("=")[1];
        int id = Integer.parseInt(idStr);

        boolean rst = delete(id);

        if (!rst){
            System.out.println("%d번 명언이 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private boolean delete(int deleteTarget) {

        int foundIndex = -1;

        for (int i = 0; i <= lastwiseSayingIndex; i++){
            WiseSaying foundedWiseSaying = wiseSayings[i];
            if (deleteTarget == foundedWiseSaying.id){
                foundIndex = i;
            }
        }
        if (foundIndex == -1) return false;

        for (int i = foundIndex; i < lastwiseSayingIndex; i++){
            wiseSayings[i] = wiseSayings[i+1];
        }
            lastwiseSayingIndex--;
            return true;
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        WiseSaying[] foundedWiseSayings = findList();

        for (WiseSaying wiseSaying : foundedWiseSayings){
            System.out.printf("%d / %s / %s\n",wiseSaying.id,wiseSaying.author,wiseSaying.content);
        }
    }

    private WiseSaying[] findList() {
        WiseSaying[] foundedWiseSayings = new WiseSaying[lastwiseSayingIndex + 1];
        int foundedWiseSayingIndex = -1;
        for (int i = lastwiseSayingIndex; i >= 0; i--){
            WiseSaying foundedWiseSaying = wiseSayings[i];
            foundedWiseSayings[++foundedWiseSayingIndex] = foundedWiseSaying;
        }
        return foundedWiseSayings;
    }

    private void actionWrite() {
        System.out.println("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가 : ");
        String author = sc.nextLine();

        write(content,author);
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }

    private void write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings[++lastwiseSayingIndex] = wiseSaying;
    }
}
