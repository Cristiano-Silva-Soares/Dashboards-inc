import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/User';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user: User = new User;
  confirmPassword: string;


  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    window.scroll(0,0)
  }

  confirmingPassword(event: any) {
    this.confirmPassword = event.target.value;
  }

  register() {

    if(this.user.password != this.confirmPassword) {
      console.error(400);
    } else {
      this.userService.register(this.user).subscribe((resp: User)=> {
        this.user = resp;
        this.router.navigate(['/login'])
        alert("Succesfull Login!")
      })
    }
  }

}
