package com.pocketmath;

public class SequentialThreading {

	public static void main(String[] args) throws InterruptedException {
		Foo foo = new Foo();

		// Creating 3 threads
		// t1 - first
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					foo.first();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// t2 - second
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					foo.second();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// t3 - third
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					foo.third();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// Spawning threads : Have started threads as t1, t3, t2 to ensure that
		// stil it executes in t1, t2, t3 because of locks
		t1.start();
		t3.start();
		t2.start();

		// Waiting for threads to complete
		t1.join();
		t2.join();
		t3.join();

	}

}

/*
 * Foo class has 3 methods : first, second, third It has LockValues lockVal
 * Whatever is the value of lockVal, that function will be allowed to run
 */
class Foo {

	private LockValues lockVal = LockValues.FIRST;

	Foo() {
		System.out.println("Foo.Foo() : Constructor");
	}

	void first() throws InterruptedException {
		while (lockVal != LockValues.FIRST) {
			Thread.sleep(100);
		}

		if (lockVal == LockValues.FIRST) {
			System.out.println("Foo.first() process");
			lockVal = LockValues.SECOND;
		}
	}

	void second() throws InterruptedException {
		while (lockVal != LockValues.SECOND) {
			Thread.sleep(100);
		}

		if (lockVal == LockValues.SECOND) {
			System.out.println("Foo.second() process");
			lockVal = LockValues.THIRD;
		}
	}

	void third() throws InterruptedException {
		while (lockVal != LockValues.THIRD) {
			Thread.sleep(100);
		}

		if (lockVal == LockValues.THIRD) {
			System.out.println("Foo.third() process");
			lockVal = LockValues.FIRST;
		}
	}
}

enum LockValues {
	FIRST, SECOND, THIRD
}
