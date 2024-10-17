# 자바스크립트 비동기 처리 

----
자바스크립트에서는 setTimeout을 처리할 때 비동기 API가 작동해 JS엔진이 아닌 특정 큐에 저장한다(JS의 메인엔진은 1개) , 이때 메인 쓰레드는 task queue를 확인하며 
실행하기 때문에 비동기식 처리가 가능한 것이다.

## 동기적 흐름
* 동기적 흐름은 현재 실행중인 코드가 종료되기 전까지 다음 단계를 실행하지 않는 것이다.
* 특히 싱글 쓰레드 환경에서 메인 쓰레드를 길게 점유할 경우 프로그램의 성능이 저하된다

```
let a = 10;
let b = 5;
console.log("a = ",a);

function calc(num){
    for(let i = 0; i < a; ++i){
        console.log(num);
    }
}

calc(a);
calc(b);
```

위 코드에서 calc(a)가 실행되고 나서 종료되어야지만 calc(b)가 실행되는 것이 동기식 흐름이다.

## 비동기적 흐름
* 현재 진행중인 작업과는 별개로 어떠한 트랜잭션을 수행하는것을 의미한다

```
let a = 10

setTimeout(function callback() { 
  	console.log('a : ', a)
}, 3000) 

console.log('Finished')

//Finished
//a: 10
```
위 코드에서 setTimeout이 비동기적으로 실행되어 task queue에 들어간다,
따라서 메인쓰레드는 다음 작업을 수행할 수 있다.

## Promise
* 비동기 작업을 표현하는 객체
* 비동기 처리의 순서를 표현할 수 있다
```
let promise = new Promise((resolve, reject) => {
	if (Math.random() < 0.5) { 
    	return reject("실패") //0.5보다 작으면 '실패'반환
	}
	resolve(10) //성공 시 10을 반환
 })
```
성공시 resolve, 실패시 reject를 호출

## async/await
```
//예를들면 이렇게..
function fetchData(){
	return new Promise
}

//fetchData()
//	.then() //then에서 data를 받을 수 있다
//	.catch()

async function asyncFunc() { 
	let data = await fetchData() 
    let user = await fetchUser(data)
  return user
}
```
여기서 유의할 점은 async로 정의된 함수는 반드시 Promise를 리턴해야 한다는 것이다.
마찬가지로 에러가 발생할 경우 try-catch문으로 에러를 처리할 수 있다.
```
async function asyncFunc() {
  try {
	let data1 = await fetchData1()
    return fetchData2(data1)
  } catch (e) {
	console.log("실패:", e) 
  }
}
```

이름, 주소, 나이 정보를 비동기식으로 가져온다고 가정할때 방법
```
function getName(cb) {
    setTimeout(() => {
        cb("Elice");
    }, 2000);
}

function getAge(cb) {
    setTimeout(() => {
        cb(6);
    }, 2000);
}

function getAddress(cb) {
    setTimeout(() => {
        cb("Seoul");
    }, 2000);
}
```
console.log를 3번호출 하면 되겠지만 한번만 사용하고자 했을때 다음과 같은 방법이 있을것이다.
```
getName((name) => {
    getAge((age) => {
        getAddress((address) => {
            console.log(name, age, address)
        })
    })
})
```
비동기 함수가 3개 쓰이고 시간이 6초뒤에 정보가 출력될 것 입니다, 지금은 콜백이 3개만 쓰였지만 더 많이 쓰인다면
정말 복잡해질것 입니다, 콜백지옥에서 벗어나기 위해선 Promise를 쓰는것이 좋아보입니다.

```
function getName() {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve("Elice");
        }, 2000);
    })
}

function getAge() {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(6);
        }, 2000);
    })
}

function getAddress() {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve("Seoul");
        }, 2000);
    })
}
```
Promise를 사용하여 위 코드를 변경하였습니다, Promise를 2초뒤에 리턴하므로 실행코드를 다음과 같이 구성할 수 있습니다.
```
getName().then((res) => {
    console.log(res);
})

getAge().then((res) => {
    console.log(res);
})

getAddress().then((res) => {
    console.log(res);
})
```
우리는 Promise가 배열을 받을수 있다는 사실을 알고있습니다, 따라서 다음과 같이 사용할 수 있습니다.
```
Promise
    .all([getName(), getAge(), getAddress()])
    .then((res) => {
        const [name, age, address] = res;
        console.log(name, age, address)
    })
```
배열안의 함수들은 모두 Promise 객체를 반환하기 때문에 Promise 배열을 구성할 수 있습니다, 비동기적으로 실행되기 때문에
실행시간은 2초가 걸리고 출력이 완료됩니다.

async / await를 이용하여 위 문제를 해결할수도 있습니다, 
```
(async () => {
    const name = await getName();
    const age = await getAge();
    const address = await getAddress();

    console.log(name, age, address);
})();
```
이 경우 await가 resolve될때까지 기다린후에 다음 순서로 넘어가기 때문에 실행시간은 6초가 소요됩니다.
