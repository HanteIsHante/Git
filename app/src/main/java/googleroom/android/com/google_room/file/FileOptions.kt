package googleroom.android.com.google_room.file

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.*

/**
 *  Created By handan
 *  CreateDate: 2018/6/8
 *  Desc:
 */
class FileOptions(context: Context) {

    private val fileName: String = "googleRoom.txt"
    private var file: File


    /**
     * 创建应用内部存储文件
     */
    init {
        file = File(context.filesDir, fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    /**
     * 判断外部存储卡是否可用
     * 获取外部存储卡状态
     * Environment.MEDIA_MOUNTED_READ: 可读写
     * Environment.MEDIA_MOUNTED_READ_ONLY： 可读
     * 返回的状态为 MEDIA_MOUNTED 时 表示为可对文件进行读写
     */
    fun isExternalStorageWritable(): Boolean {
        val externalStorageDirectory = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == externalStorageDirectory) {
            return true
        }
        return false
    }


    /**
     * 字节流
     */
    fun fileOutPutStream(msg: String) {
        val fileOutputStream = FileOutputStream(file)
    }

    /**
     * 读取文件
     */
    fun readFile(): StringBuffer {
        val fileReader = FileReader(file)
        val bufferedReader = BufferedReader(fileReader)
        var stringBuffer = StringBuffer()
        while (bufferedReader.readLine() != null) {
            Log.d("<----", "Read LOG: ${bufferedReader.readLine()}")
            stringBuffer = stringBuffer.append(bufferedReader.readLine() + "\n")
        }
        return stringBuffer
    }

    /**
     * 读取文件
     */
    fun read(): String {
        var inputStream: FileInputStream? = null
        var stringBuffer = String()
        try {
            inputStream = FileInputStream(file)
            val available = inputStream.available()
            val buffer = ByteArray(available)
            inputStream.read(buffer)
            stringBuffer = buffer.toString()
            System.out.print("Read Content:  $buffer")
        } catch (_: Exception) {
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
        return stringBuffer
    }

    fun write(writeMsg: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            // 第二个参数设置为 true 表示添加在内容之后
            fileOutputStream = FileOutputStream(file, true)


        } catch (e: Exception) {
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close()
            }
        }

    }

    /**
     * 写入文件
     */
    fun writeFile(msg: String) {
        // 以追加方式, 第二个参数设置为 true 表示添加在内容之后
        var fileWriter: FileWriter? = null
        var bufferedWriter: BufferedWriter? = null
        try {
            fileWriter = FileWriter(file, true)
            bufferedWriter = BufferedWriter(fileWriter)
            bufferedWriter.write("\r\n")
            bufferedWriter.write(msg)
        } catch (e: Exception) {
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.flush()
                bufferedWriter.close()
            }
            if (fileWriter != null) {
                fileWriter.close()
            }
        }
    }
}