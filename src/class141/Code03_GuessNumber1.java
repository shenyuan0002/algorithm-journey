package class141;

// 猜数字(中国剩余定理解决)
// 测试链接 : https://www.luogu.com.cn/problem/P3868

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code03_GuessNumber1 {

	public static int MAXN = 11;

	public static long a[] = new long[MAXN];

	public static long b[] = new long[MAXN];

	public static long d, x, y, px, py;

	public static void exgcd(long a, long b) {
		if (b == 0) {
			d = a;
			x = 1;
			y = 0;
		} else {
			exgcd(b, a % b);
			px = x;
			py = y;
			x = py;
			y = px - py * (a / b);
		}
	}

	public static long multiply(long a, long b, long mod) {
		long ans = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				ans = (ans + a) % mod;
			}
			a = (a + a) % mod;
			b >>= 1;
		}
		return ans;
	}

	// 中国剩余定理模版
	public static long crt(int n) {
		long m = 1;
		for (int i = 1; i <= n; i++) {
			m = m * a[i];
		}
		long mi, tmp, ans = 0;
		for (int i = 1; i <= n; i++) {
			mi = m / a[i];
			exgcd(mi, a[i]);
			x = (x % m + m) % m;
			tmp = multiply(multiply(mi, x, m), b[i], m);
			ans = (ans + tmp) % m;
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		int n = (int) in.nval;
		for (int i = 1; i <= n; i++) {
			in.nextToken();
			b[i] = (long) in.nval;
		}
		for (int i = 1; i <= n; i++) {
			in.nextToken();
			a[i] = (long) in.nval;
		}
		// 题目输入负数要转化成正数
		for (int i = 1; i <= n; i++) {
			b[i] = (b[i] % a[i] + a[i]) % a[i];
		}
		out.println(crt(n));
		out.flush();
		out.close();
		br.close();
	}

}