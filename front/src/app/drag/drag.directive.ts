import {Directive, EventEmitter, HostBinding, HostListener, Output} from '@angular/core';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';

@Directive({
  selector: '[appDrag]'
})
export class DragDirective {

  @Output() files: EventEmitter<FileList> = new EventEmitter();

  @HostBinding("style.background") private background = "#FFF";

  constructor() { }

  @HostListener("dragover", ["$event"]) public onDragOver(evt: DragEvent) {
    evt.preventDefault();
    evt.stopPropagation();
    this.background = "#EEE";
  }

  @HostListener("dragleave", ["$event"]) public onDragLeave(evt: DragEvent) {
    evt.preventDefault();
    evt.stopPropagation();
    this.background = "#FFF";
  }

  @HostListener('drop', ['$event']) public onDrop(evt: DragEvent) {
    evt.preventDefault();
    evt.stopPropagation();
    this.background = '#FFF';

    if (evt.dataTransfer !== null && evt.dataTransfer !== undefined && evt.dataTransfer.files.length > 0) {
      this.files.emit(evt.dataTransfer.files);
    }

  }

}
