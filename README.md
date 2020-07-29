# Kryptic

Kryptic is a fast crypto toolkit for **kotlin** and **java**. 

This library is an object-oriented cryptography toolkit that implements several fundamental cryptographic algorithms (**Hash**, **Signature**, **Symmetric**, **key-generation**) including **Blowfish**, **AES**, **DES**, **RC2**, and **SHA**(`1`, `224`, `256`, `384`, `512`, `512/224`, `512/256`), **SHA3**(`224`, `256`, `384`, `512`,), **MD**(`2`, `5`), **HMAC**,  for `Kotlin` and `Java`.
The unique feature of this library is simplicity and easy code writing. All this in only `70 kb`.

- `kryptic` for Java +8 - [download jar](https://raw.githubusercontent.com/MerabetAmine/Kryptic/master/Kryptic.jar)



## Using the library

You can download and create the library source code as you like. Or use the jar file located above.

### Symmetric :

Symmetric-key algorithms are [algorithms](https://en.wikipedia.org/wiki/Algorithm) for [cryptography](https://en.wikipedia.org/wiki/Cryptography) that use the same [cryptographic keys](https://en.wikipedia.org/wiki/Key_(cryptography)) for both encryption of plaintext and decryption of ciphertext. The keys may be identical or there may be a simple transformation to go between the two keys. The keys, in practice, represent a shared secret between two or more parties that can be used to maintain a private information link.

#### *KSymmetric*

| Method          | Args type                 | Return type  |
| --------------- | ------------------------- | ------------ |
| encrypt         | ByteArray ***OR*** String | ByteArray    |
| decrypt         | ByteArray ***OR*** String | ByteArray    |
| encryptToString | ByteArray ***OR*** String | String       |
| decryptToString | ByteArray ***OR*** String | String       |
| key             | ByteArray ***OR*** String | *KSymmetric* |
| iv              | ByteArray ***OR*** String | *KSymmetric* |



#### Usage

- ##### `AES`

  ```kotlin
  // AES() / AES("key string") / AES(keyByteArray)
  // AES128() Default ECB or use AES128.CBC / AES128.CFB / AES128.OFB
  // AES192() Default ECB or use AES192.CBC / AES192.CFB / AES192.OFB
  // AES256() Default ECB or use AES256.CBC / AES256.CFB / AES256.OFB
  val aes = AES256.CBC
  aes.key(key) // key String or ByteArray
     .iv(iv)   // IV String or ByteArray
  val bytes = aes.encrypt(data) // return ByteArray
  val string = aes.encryptToString(data) // return String
  ```

- ##### `DES / TDES`

  ```kotlin
  // DES() Default ECB or use DES.CBC / DES.CFB / DES.OFB
  val des = DES.CFB.key(key).iv(iv)
  val e = des.encryptToString(data) 
  val d = des.decryptToString(e)
  
  // TDES() Default ECB or use TDES.CBC / TDES.CFB / TDES.OFB
  val tdes = TDES.CFB.key(key).iv(iv)
  val e = tdes.encryptToString(data) 
  val d = tdes.decryptToString(e)
  ```

- ##### `RC2`

  ```kotlin
  // RC2() / RC2("key string") / RC2(keyByteArray)
  val rc2 = RC2(key).key(key)
  val e = rc2.encryptToString(data) 
  val d = rc2.decryptToString(e)
  ```

- ##### `Blowfish`

  ```kotlin
  // Blowfish() Default ECB or use Blowfish.CBC / Blowfish.CFB / Blowfish.OFB
  val e = Blowfish.CFB.key(key).iv(iv).encrypt(data)
  ```



### Hashing :

A cryptographic hash function is a mathematical [algorithm](https://en.wikipedia.org/wiki/Algorithm) that [maps](https://en.wikipedia.org/wiki/Map_(mathematics)) data of arbitrary size (often called the "message") to a bit array of a fixed size (the "hash value", "hash", or "message digest"). It is a [one-way function](https://en.wikipedia.org/wiki/One-way_function), that is, a function which is practically infeasible to invert. 

#### *HashWithoutKey*

| Method                                                    | Args type                 | Return type |
| --------------------------------------------------------- | ------------------------- | ----------- |
| invoke *(get hash)*<br>kotlin code: hashObj**(**data**)** | ByteArray ***OR*** String | String      |

#### *HashWithKey*

| Method             | Args type                 | Return type   |
| ------------------ | ------------------------- | ------------- |
| bytes              | ByteArray ***OR*** String | ByteArray     |
| string             | ByteArray ***OR*** String | String        |
| invoke *(set Key)* | ByteArray ***OR*** String | *HashWithKey* |



#### Usage

- ##### `MD`

  ```kotlin
  val data = "ABCD" // String or ByteArray
  MD._2(data) // return String
  MD._5(data)
  // OR
  val md2 = MD._2
  md2(data) // return String
  ```

- ##### `SHA`

  ```kotlin
  val data = ... // String or ByteArray
  SHA(data) // Default SHA1
  SHA._224(data)
  SHA._256(data)
  SHA._384(data)
  SHA._512(data)
  SHA._512d224(data) // Not Available on Java 8
  SHA._512d256(data) // Not Available on Java 8
  ```

- ##### `SHA3`

  ```kotlin
  // SHA3 Not Available on Java 8
  val data = ... // String or ByteArray
  SHA3._224(data) // return String
  SHA3._256(data)
  SHA3._384(data)
  SHA3._512(data)
  ```

- ##### `HMac`

  ```kotlin
  val data = ... // String or ByteArray                           
  val key = .... // String or ByteArray                             
   
  val hmacMd5 = HMac.MD5
  println(hmacMd5(key).bytes(data)) // return ByteArray
  // OR 
  HMac.MD5(key).bytes(data) // return String
  HMac.SHA1(key).string(data)    
  HMac.SHA224(key).string(data)     
  HMac.SHA256(key).string(data)     
  HMac.SHA512(key).string(data) 
  HMac.SHA512d224(key).string(data) // Not Available on Java 8
  HMac.SHA512d256(key).string(data) // Not Available on Java 8
  ```

- ##### `SslMac`

  ```kotlin
  val data = ... // String or ByteArray                           
  val key = .... // String or ByteArray 
  val sslMacMd5 = HMac.MD5
  sslMacMd5(key).bytes(data) // return ByteArray
  // OR
  SslMac.MD5(key).string(data)  // return String
  SslMac.SHA1(key).string(data)
  ```

- ##### `Fletcher`

  ```kotlin
  val data = ... // String or ByteArray
  Fletcher(data) // return String
  ```



### Signature :

The Signatures class is used to provide applications the functionality of a digital signature algorithm. Digital signatures are used for authentication and integrity assurance of digital data.

#### *KSignature*

| Method       | Args type                 | Return type                                                  |
| ------------ | ------------------------- | ------------------------------------------------------------ |
| sign         | ByteArray ***OR*** String | **Pair**<ByteArray, KeyPair>?<br>*Pair*(`signature`, `keyPair`) |
| signToString | ByteArray ***OR*** String | **Triple**<String, String, String>? <br>*Triple*(`signature`, `publicKey`, `privateKey`) |



#### Usage

- ##### `MD`

  ```kotlin
  // MD2withRSA / MD5withRSA / MD5andSHA1withRSA
  val data = ... // String or ByteArray
  MD._2.RSA.signToString(data) // return Triple<String, String, String>?
  MD.SHA.RSA.sign(data) // return Pair<ByteArray, KeyPair>?
  ```

- ##### `SHA`

  ```kotlin
  // SHA1withRSA / SHA256withDSA / SHA256withRSA / SHA384withRSA
  // SHA512withRSA / SHA512/224withRSA / SHA512/256withRSA
  val data = ... // String or ByteArray
  SHA.RSA.signToString(data)         
  SHA._256.DSA.sign(data) 
  .
  . 
  SHA._512d224.RSA.signToString(data) // Not Available on Java 8
  SHA._512d256.RSA.signToString(data) // Not Available on Java 8
  ```

- ##### `NONE`

  ```kotlin
  // NONEwithDSA / NONEwithRSA
  val data = ... // String or ByteArray
  NONE.DSA.sign(data)
  NONE.RSA.sign(data)
  ```



### Generator :

This package provides the functionality of a secret (symmetric) key generator. [KeyGenerator](https://javadoc.scijava.org/Java7/javax/crypto/KeyGenerator.html) and [KeyPairGenerator](https://javadoc.scijava.org/Java7/java/security/KeyPairGenerator.html).

* ##### `KGene`

  | Method    | Args type                 | Return type |
  | --------- | ------------------------- | ----------- |
  | invoke    | String (*algorithm name*) | *KGene?*    |
  | invoke    | Int (Key size)            | *KGene?*    |
  | secretKey | /                         | SecretKey?  |
  | byteArray | /                         | ByteArray?  |
  | string    | /                         | String      |

  ```kotlin
  val kgene = KGene("algorithm name")
  // OR you can access to algorithm by KGene.Name
  // Algorithms: {AES, ARCFOUR, Blowfish, ChaCha20, DES, DESede, TDES, HmacMD5, 
  // HmacSHA1, HmacSHA224, HmacSHA256, HmacSHA384, HmacSHA512, RC2, SunTls12Prf,
  // SunTlsKeyMaterial, SunTlsMasterSecret, SunTlsPrf, SunTlsRsaPremasterSecret}  
  
  val kgene = KGene.AES // OR KGene.AES(keySize)
  secretKey = kgene.secretKey() // return SecretKey
  key = kgene.byteArray()  // return key as ByteArray
  
  key = KGene.AES(keySize).string() // return key as String
  ```

* ##### `KPair`

  | Method             | Args type                                   | Return type                                                  |
  | ------------------ | ------------------------------------------- | ------------------------------------------------------------ |
  | invoke             | String (*algorithm name*)                   | *KPair?*                                                     |
  | invoke             | Int (Key size)                              | *KPair?*                                                     |
  | keyPair            | /                                           | KeyPair?                                                     |
  | keyPairAsByteArray | /                                           | **Pair**<ByteArray, ByteArray>?<br>*Pair*(`publicKey`, `privateKey`) |
  | keyPairAsString    | /                                           | **Pair**<String, String>?<br/>*Pair*(`publicKey`, `privateKey`) |
  | publicKey          | ByteArray ***OR*** String<br>(public key)   | PublicKey?                                                   |
  | privateKey         | ByteArray ***OR*** String<br/>(private key) | PrivateKey?                                                  |

  ```kotlin
  val kpair = KPair("algorithm name")
  // OR you can access to algorithm by KPair.Name
  // Algorithms: {DSA, DiffieHellman, EC, RSA, RSASSA, RSASSA, X25519, X448, XDH}  
  
  val kpair = KPair.RSA // OR KPair.RSA(keySize)
  keyPair = kpair.keyPair() // return KeyPair
  key = kpair.keyPairAsByteArray() // return key as Pair<ByteArray, ByteArray>?
  key = kpair.keyPairAsString() // return key as Pair<String, String>?
  ```

  

