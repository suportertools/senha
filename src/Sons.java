
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

@SuppressWarnings("unchecked")
public class Sons {

    private String audio;
    private File arqAudio;
    private SourceDataLine sDLine;
    private AudioInputStream aIOStream;
    private Thread aux;
    private boolean flag;

    public Sons() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        // PEGA O ARQUIVO COM O AUDIO
        this.audio = ("c:/projetos/audio/senha.mp3");

        arqAudio = new File(audio);
        create();
        aux = new Thread(new Runnable() {
            public void run() {
            }
        });
    }

    private void create() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        AudioInputStream in = AudioSystem.getAudioInputStream(arqAudio);
        AudioInputStream din = null;
        if (in != null) {
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                    baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                    false);
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            sDLine = getLine(decodedFormat);
            aIOStream = din;
        }
    }

    public void play() throws IOException, LineUnavailableException {
        start();
        aux.interrupt();
        aux = new Thread(new Runnable() {
            public void run() {
                rawplay();
            }
        });
        aux.start();
    }

    private void play(byte[] data) throws IOException {
        try {
            // LE OS BITS DO ARQUIVO
            int length = aIOStream.read(data, 0, data.length);
            // INICIA OS BITS
            if (length == -1) {
                flag = false;
                return;
            }
            sDLine.write(data, 0, length);
        } catch (IOException e) {
            throw e;
        }
    }

    private void start() {
        sDLine.start();
        flag = true;
    }

    private void rawplay() {
        byte[] data = new byte[4096];
        try {
            while (flag) {
                play(data);
            }
        } catch (IOException e) {
            System.out.println("FIM DO AUDIO");
        }
    }

    public void close() {
        aux.interrupt();
        sDLine.drain();
        sDLine.stop();
        sDLine.close();
        try {
            aIOStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        aux.interrupt();
        sDLine.stop();
        sDLine.drain();
        flag = false;
    }

    public void resume() {
        sDLine.start();
        flag = true;
        try {
            play();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private SourceDataLine getLine(AudioFormat audioFormat)
            throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open(audioFormat);
        return res;
    }

    public String getNomeMusica() throws UnsupportedAudioFileException,
            IOException {

        AudioFileFormat baseFileFormat = AudioSystem
                .getAudioFileFormat(arqAudio);
        Map<String, String> propriedade = ((TAudioFileFormat) baseFileFormat)
                .properties();

        String nome = propriedade.get("author");
        nome += " - " + propriedade.get("title");

        return nome;
    }

    public String getExtensao() {
        StringBuilder sb = new StringBuilder();
        for (int i = (audio.length() - 3); i < audio.length(); i++) {
            sb.append(audio.charAt(i));
        }
        return sb.toString().toUpperCase();
    }

    private boolean checarExtensao() {
        String a = "MP3";
        String b = "OGG";
        return (a.contentEquals(getExtensao()) || b.contentEquals(getExtensao()));
    }

    public String getQualidade() throws UnsupportedAudioFileException,
            IOException {

        AudioFileFormat baseFileFormat = AudioSystem
                .getAudioFileFormat(arqAudio);
        Map<String, String> propriedade = ((TAudioFileFormat) baseFileFormat)
                .properties();

        if (checarExtensao() == true) {
            String buffer = "";
            String key = getExtensao().toLowerCase();

            key += ".bitrate.nominal.bps";
            buffer = (String) propriedade.get(key).toString();
            int aux = Integer.parseInt(buffer) / 1000;
            buffer = Integer.toString(aux) + " Kbps | ";

            key = getExtensao().toLowerCase();
            key += ".frequency.hz";
            key = (String) propriedade.get(key).toString();
            aux = Integer.parseInt(key) / 1000;
            buffer += Integer.toString(aux) + " Khz";

            return buffer;

        } else {
            return "??? Kbps | ?? Khz";
        }
    }

    public void setMusica(String musica) {
        this.audio = musica;
        arqAudio = new File(this.audio);
    }
}
