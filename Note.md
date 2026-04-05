# Java Backend Learning
## Data Structure
## Internet Programming & Java Net Library
### Network Communication
- ### Basic Communication Frame
  - ### Client -> Server (CS)
    - #### Client: Developed by programmers, and users need to download the client
    - #### Server: Developed by programmers
  - ### Browser -> Server (BS)
    - #### Browser: Do not need programmers to develop, but users need to download browser
    - #### Server: Developed by programmers
  - | Name | Tech    | Needs Development by Programmer |
    |------|---------|:-------------------------------:|
    | CS   | Client  |               YES               |
    | BS   | Browser |               NO                |
  - ### Both CS and BS must rely on *Internet Programming*
- ### Three Essential Elements of Network Communication
  - ### IP Address
    - ### Internet Protocol, it's the only sign assigned to device
    - ### IPv4:
      - ### Based on 32 bits or 4 bytes, 8 bits as a group
      - ### E.g: 11000000 10101000 00000001 01000010
      - ### Use Dot-decimal Notation to represent an IP address
        ### Like 192.168.1.1 (0-255 for each digit)
    - ### IPv6:
      - ### Based on 128 bits or 16 bytes
        - ### E.g: 
            | Index |       encode        | result |
            |:-----:|:-------------------:|:------:|
            |   1   | 0010 0000 1000 0010 | 0x2082 |
            |   2   | 0000 0000 0100 0100 | 0x0044 |
            |   3   | 0100 0010 0100 1000 | 0x4248 |
            |   4   | 0000 0001 0001 0001 | 0x0111 |
            |   5   | 0001 0000 0000 0000 | 0x1000 |
            |   6   | 1111 1100 1001 1010 | 0xFC9A |
            |   7   | 1001 0001 0000 0000 | 0x9100 |
            |   8   | 0101 1010 1010 0101 | 0x5AA5 |
      
      - ### Use Colon Hexadecimal Notation to represent an IP address
      - ### Like 2082:0044:4248:0111:1000:fc9a:9100:5aa5 
        ### (0000-FFFF for each group; 0-F for each digit)
    - ### IP Domain Name
      - ### The name showed to human beings, easier to remember than address
      - ### E.g www.baidu.com; www.itcast.cn; 
      - ### Domain Name System (DNS)
      - 
    - 
        
  - ### Port
  - ### Protocol