package main

import (
	"fmt"
	"time"
)

func main() {
	for {
		fmt.Println("Hello tellurics!")

		time.Sleep(time.Second * 1)
	}
}
